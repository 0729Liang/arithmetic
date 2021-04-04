package other;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Scanner;

public class JebKeygen {

    public static void main(String[] paramArrayOfString) {
        Keygen keygen = new Keygen();
        String str = keygen.getKeys();
        System.out.println("key:" + str);
    }

    public static class Keygen {
        private long[] getMachineSerialNumber() {
            long[] arrayOfLong = new long[2];
            String str = System.getProperty("os.name", "");
            if (str.startsWith("Windows")) {
                String str1 = getWinSerialNumber("wmic csproduct get uuid", "UUID");
                str1 = str1 + "__";
                String str2 = getWinSerialNumber("wmic bios get serialnumber", "SerialNumber");
                str1 = str1 + str2;
                arrayOfLong[0] = getStringMd5(str1);
                arrayOfLong[1] = getStringMd5(str2);
                return arrayOfLong;
            }
            if (str.startsWith("Mac")) {
                arrayOfLong[0] = getStringMd5(getMacSerialNumber());
                arrayOfLong[1] = 0L;
                return arrayOfLong;
            }
            if (str.startsWith("Linux")) {
                arrayOfLong[0] = getStringMd5(getLinuxSerialNumber());
                arrayOfLong[1] = 0L;
                return arrayOfLong;
            }
            return null;
        }

        private long getStringMd5(String paramString) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(paramString.getBytes());
                ByteBuffer byteBuffer;
                (byteBuffer = ByteBuffer.wrap(messageDigest.digest())).order(ByteOrder.LITTLE_ENDIAN);
                return byteBuffer.getLong() & Long.MAX_VALUE;
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                noSuchAlgorithmException.printStackTrace();
                return 0L;
            }
        }

        private String getMacSerialNumber() {
            try {
                Process process = Runtime.getRuntime().exec("/usr/sbin/system_profiler SPHardwareDataType".split(" "));
                OutputStream outputStream = process.getOutputStream();
                InputStream inputStream = process.getInputStream();
                try {
                    String str1;
                    outputStream.close();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String str3 = "Serial Number";
                    try {
                        StringBuilder sb = new StringBuilder();
                        while((str1 = bufferedReader.readLine())!=null) {
                            sb.append(str1);
                        }
                        str1 = sb.toString();
                    } catch (IOException iOException) {
                        String str = iOException.toString();
                        return null;
                    } finally {
                        try {
                            inputStream.close();
                        } catch (IOException iOException) {
                            return null;
                        }
                    }
                    String str2 = str1.split(str3)[1];
                    int i = str2.indexOf(':');
                    if (i >= 0) {
                        str2 = str2.substring(i + 1).trim();
                        return str2;
                    }
                    return str2;
                } catch (IOException iOException) {
                    return null;
                }
            } catch (IOException iOException) {
                return null;
            }
        }

        private String getLinuxSerialNumber() {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/var/lib/dbus/machine-id")));
                try {
                    String str = bufferedReader.readLine();
                    if (str != null) {
                        str = str.trim();
                    } else {
                        str = null;
                    }
                    return str;
                } catch (IOException iOException) {
                    String str = iOException.toString();
                    return null;
                } finally {
                    try {
                        bufferedReader.close();
                    } catch (IOException iOException) {
                        return null;
                    }
                }
            } catch (FileNotFoundException fileNotFoundException) {
                return null;
            }
        }

        private String getWinSerialNumber(String paramString1, String paramString2) {
            String str = null;
            try {
                Process process = Runtime.getRuntime().exec(paramString1.split(" "));
                OutputStream outputStream = process.getOutputStream();
                InputStream inputStream = process.getInputStream();
                outputStream.close();
                Scanner scanner = new Scanner(inputStream);
                do {
                    try {
                        if (!scanner.hasNext())
                            break;
                    } catch (Throwable throwable) {
                        scanner.close();
                    }
                } while (!paramString2.equals(scanner.next()));
                str = scanner.next().trim();
                scanner.close();
            } catch (IOException iOException) {
                System.out.println(iOException.toString());
            }
            return str;
        }

        public String getKeys() {
            Random random = new Random(System.currentTimeMillis());
            long l1 = (1624054740 + random.nextInt(10000000));
            int i = (int) (l1 ^ 0x56739ACDL);
            int j = i;
            int k = 0;
            while (j > 0) {
                k += j & 0xF;
                j >>= 4;
            }
            long[] arrayOfLong = getMachineSerialNumber();
            System.out.println(arrayOfLong[0]);
            System.out.println(arrayOfLong[1]);
            long l2 = 0L;
            if (arrayOfLong[0] != 0L) {
                l2 = arrayOfLong[0];
            } else {
                l2 = arrayOfLong[1];
            }
            int m = (int) l2;
            int n = (int) (l2 >> 32L);
            int i1 = n - 0 + 1432778632 & Integer.MAX_VALUE;
            int i2 = m + 1337 + 287454020;
            String str = String.format("%x", new Object[]{Integer.valueOf(i1)}) + String.format("%x", new Object[]{Integer.valueOf(i2)});
            return Long.parseLong(str, 16) + "Z" + i + (k % 10);
        }


    }

}
