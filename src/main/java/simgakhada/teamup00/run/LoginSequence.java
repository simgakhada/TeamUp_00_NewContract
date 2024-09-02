package simgakhada.teamup00.run;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * LoginSequence
 * 비밀번호를 통한 잠금 기능을 담당하는 클래스입니다.
 * 비밀번호는 SettingDAO에서 관리합니다.
 */
public class LoginSequence
{
    public static void loginSequence()
    {
        File path = new File("src/main/resources/config/settings.properties");
        Properties prop = new Properties();
        FileInputStream fis;
        Scanner sc = new Scanner(System.in);
        MainController mainController = new MainController();
        int cnt = 0;
        try {
            fis = new FileInputStream(path);
            prop.load(fis);
            fis.close();
            if(prop.getProperty("locked").equals("true"))
            {
                System.out.println("[전화번호부] 전화번호부가 암호화되어 인증이 필요합니다.");
                System.out.println("비밀번호를 입력해주세요.");
                while(true)
                {
                    System.out.print("비밀번호 입력: ");
                    String password = sc.nextLine();
                    if(password.equals(prop.getProperty("password")))
                    {
                        System.out.println();
                        System.out.println("환영합니다.");
                        mainController.run();
                    }
                    else
                    {
                        System.out.println("암호 인증 실패.");
                        cnt++;
                        if(cnt == 5)
                        {
                            System.out.println("암호 입력에 5회 실패하여 프로그램이 강제 종료됩니다.");
                            System.exit(0);
                        }
                        System.out.println("앞으로 " + (5 - cnt) + "회 더 실패할 경우 프로그램이 강제 종료됩니다.");
                        System.out.println();
                    }
                }
            }
            else
            {
                System.out.println("환영합니다.");
                mainController.run();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
