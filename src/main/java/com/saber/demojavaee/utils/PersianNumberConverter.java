package com.saber.demojavaee.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class PersianNumberConverter {

    private static final String[] YEKAN = {
            "صفر", "یک", "دو", "سه", "چهار", "پنج", "شش", "هفت", "هشت", "نه",
            "ده", "یازده", "دوازده", "سیزده", "چهارده", "پانزده", "شانزده", "هفده", "هجده", "نوزده"
    };

    private static final String[] DAHGAN = {
            "", "", "بیست", "سی", "چهل", "پنجاه", "شصت", "هفتاد", "هشتاد", "نود"
    };

    private static final String[] SADGAN = {
            "", "صد", "دویست", "سیصد", "چهارصد", "پانصد", "ششصد", "هفتصد", "هشتصد", "نهصد"
    };

    private static final String[] POWERS = {
            "", "هزار", "میلیون", "میلیارد", "تریلیون", "کوادریلیون", "کویینتیلیون",
            "سکستیلیون", "سپتیلیون", "اکتیلیون", "نونیلیون", "دسیلیون"
    };

    private static final BigInteger THOUSAND = BigInteger.valueOf(1000);
    private static final BigInteger MAX_SUPPORTED = new BigInteger("10").pow(36); // Up to 10^36

    public static String convert(long number) {
        if (number == 0) {
            return "صفر";
        }

        if (number < 0) {
            return "منفی " + convert(-number);
        }

        return convertNumber(number).trim();
    }

    private static String convertNumber(long number) {
        if (number < 20) {
            return YEKAN[(int) number];
        }

        if (number < 100) {
            return DAHGAN[(int) number / 10] +
                    (number % 10 != 0 ? " و " + YEKAN[(int) number % 10] : "");
        }

        if (number < 1000) {
            return SADGAN[(int) number / 100] +
                    (number % 100 != 0 ? " و " + convertNumber(number % 100) : "");
        }

        // Handle larger numbers
        for (int i = 1; i < POWERS.length; i++) {
            long divisor = (long) Math.pow(1000, i);
            if (number < divisor * 1000) {
                return convertNumber(number / divisor) + " " + POWERS[i] +
                        (number % divisor != 0 ? " و " + convertNumber(number % divisor) : "");
            }
        }

        return "عدد بسیار بزرگ";
    }
    public static String convertWithDecimal(double number) {
        long integerPart = (long) number;
        long decimalPart = Math.round((number - integerPart) * 100);

        String integerWords = convertToWords(integerPart);

        if (decimalPart == 0) {
            return integerWords;
        } else {
            String decimalWords = convertToWords(decimalPart);
            return integerWords + " ممیز " + decimalWords;
        }
    }

    // Convert BigInteger to Persian words
    public static String convert(BigInteger number) {
        if (number.compareTo(BigInteger.ZERO) == 0) {
            return YEKAN[0];
        }

        if (number.compareTo(BigInteger.ZERO) < 0) {
            return "منفی " + convert(number.abs());
        }

        if (number.compareTo(MAX_SUPPORTED) >= 0) {
            return "عدد بسیار بزرگ";
        }

        return convertBigInteger(number).trim();
    }

    // Convert BigDecimal to Persian words with decimal places
    public static String convert(BigDecimal number) {
        return convert(number, 2); // Default 2 decimal places
    }

    public static String convert(BigDecimal number, int decimalPlaces) {
        if (number.compareTo(BigDecimal.ZERO) == 0) {
            return YEKAN[0];
        }

        // Round to specified decimal places
        BigDecimal rounded = number.setScale(decimalPlaces, RoundingMode.HALF_UP);

        BigInteger integerPart = rounded.toBigInteger();
        BigDecimal fractionalPart = rounded.remainder(BigDecimal.ONE);

        String integerWords = convert(integerPart);

        if (fractionalPart.compareTo(BigDecimal.ZERO) == 0) {
            return integerWords;
        }

        // Convert fractional part
        BigDecimal scaledFractional = fractionalPart.movePointRight(decimalPlaces);
        BigInteger fractionalValue = scaledFractional.toBigInteger();

        String fractionalWords = convert(fractionalValue);

        return integerWords + " ممیز " + fractionalWords;
    }

    private static String convertBigInteger(BigInteger number) {
        if (number.compareTo(BigInteger.ZERO) == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        int powerIndex = 0;
        BigInteger currentNumber = number;

        while (currentNumber.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divRem = currentNumber.divideAndRemainder(THOUSAND);
            BigInteger part = divRem[1];
            currentNumber = divRem[0];

            if (part.compareTo(BigInteger.ZERO) > 0) {
                String partWords = convertThreeDigit(part.intValue());

                if (powerIndex > 0) {
                    partWords += " " + POWERS[powerIndex];
                }

                if (!result.isEmpty()) {
                    result.insert(0, " و ");
                }

                result.insert(0, partWords);
            }

            powerIndex++;

            // Safety check to prevent infinite loop
            if (powerIndex >= POWERS.length) {
                break;
            }
        }

        return result.toString();
    }

    private static String convertThreeDigit(int number) {
        if (number == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        // Hundreds
        int hundreds = number / 100;
        if (hundreds > 0) {
            result.append(SADGAN[hundreds]);
        }

        // Tens and units
        int remainder = number % 100;
        if (remainder > 0) {
            if (!result.isEmpty()) {
                result.append(" و ");
            }

            if (remainder < 20) {
                result.append(YEKAN[remainder]);
            } else {
                int tens = remainder / 10;
                int units = remainder % 10;
                result.append(DAHGAN[tens]);
                if (units > 0) {
                    result.append(" و ").append(YEKAN[units]);
                }
            }
        }

        return result.toString();
    }
    public static String convertToWords(long number) {
        if (number == 0) return YEKAN[0];

        if (number < 0) {
            return "منفی " + convertToWords(Math.abs(number));
        }

        StringBuilder result = new StringBuilder();
        int powerIndex = 0;

        while (number > 0) {
            long part = number % 1000;
            if (part > 0) {
                String partWords = convertThreeDigit((int) part);
                if (powerIndex > 0) {
                    partWords += " " + POWERS[powerIndex];
                }
                if (!result.isEmpty()) {
                    result.insert(0, " و ");
                }
                result.insert(0, partWords);
            }
            number /= 1000;
            powerIndex++;
        }

        return result.toString().trim();
    }

    public static String convertWithCurrency(BigDecimal amount, String currencyName) {
        String amountWords = convert(amount);
        return amountWords + " " + currencyName;
    }

    public static String convertWithCurrency(BigInteger amount, String currencyName) {
        String amountWords = convert(amount);
        return amountWords + " " + currencyName;
    }

    // Convert very large numbers with scientific notation fallback
    public static String convertScientific(BigDecimal number) {
        if (number.compareTo(new BigDecimal(MAX_SUPPORTED)) >= 0) {
            return "عدد بسیار بزرگ: " + number.toString();
        }
        return convert(number);
    }

    public static String convertScientific(BigInteger number) {
        if (number.compareTo(MAX_SUPPORTED) >= 0) {
            return "عدد بسیار بزرگ: " + number.toString();
        }
        return convert(number);
    }

    public static String formatNumber(BigDecimal number) {
        return convert(number) + " (" + formatWithCommas(number) + ")";
    }

    public static String formatNumber(BigInteger number) {
        return convert(number) + " (" + formatWithCommas(number) + ")";
    }

    // Format number with Persian commas
    private static String formatWithCommas(BigDecimal number) {
        String numStr = number.toPlainString();
        return formatWithCommas(numStr);
    }

    private static String formatWithCommas(BigInteger number) {
        String numStr = number.toString();
        return formatWithCommas(numStr);
    }

    private static String formatWithCommas(String numStr) {
        boolean isNegative = numStr.startsWith("-");
        if (isNegative) {
            numStr = numStr.substring(1);
        }

        int dotIndex = numStr.indexOf('.');
        String integerPart = dotIndex == -1 ? numStr : numStr.substring(0, dotIndex);
        String fractionalPart = dotIndex == -1 ? "" : numStr.substring(dotIndex);

        StringBuilder result = new StringBuilder();
        int count = 0;

        for (int i = integerPart.length() - 1; i >= 0; i--) {
            if (count == 3) {
                result.append('،');
                count = 0;
            }
            result.append(integerPart.charAt(i));
            count++;
        }

        String formatted = result.reverse().toString() + fractionalPart;
        return isNegative ? "-" + formatted : formatted;
    }
}
