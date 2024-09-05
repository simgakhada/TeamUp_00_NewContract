package simgakhada.teamup00.settings;

import simgakhada.teamup00.etc.ScriptColors;
import simgakhada.teamup00.run.MainScripts;
import simgakhada.teamup00.settings.settingsenum.Search;
import simgakhada.teamup00.settings.settingsenum.Sort;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;

import static simgakhada.teamup00.template.JDBCTemplate.getConnection;

/**
 * "설정"
 * 사용자의 현재 설정을 출력하고, 새로운 기준으로 저장하는 역할을 한다.
 * 설정에는 검색 조건, 정렬 방식이 있다.
 *
 * 기존 대비 변경점
 * 현재 설정의 상태를 보여주는 기능은 printSavedCondition 메소드로 이관하고,
 * 각 save~~ 메소드에서도 저장 후 결과를 출력하도록 변경하였습니다.
 * 단순 while, switch... case문은 SettingsController 클래스를 별도로 선언하여
 * 해당 클래스에 기능을 이관하였습니다.
 */
public class SettingsService
{
    MainScripts m = new MainScripts();
    SettingsScripts s = new SettingsScripts();
    File path = new File("src/main/resources/config/settings.properties");
    Properties prop = new Properties();
    FileInputStream fis;
    FileOutputStream fos;
    Search search;
    Sort sort;
    int condition = 0;

    public void printSavedCondition()
    {
        try {
            fis = new FileInputStream(path);
            prop.load(fis);
            fis.close();
            System.out.println();
            System.out.println("현재 저장된 검색 및 정렬 기준에 대한 설정을 확인합니다.");
            if(prop.getProperty("search").equals("0") && prop.getProperty("sort").equals("0"))
            {
                System.out.println("현재 저장된 설정이 존재하지 않습니다.");
                System.out.println("이전으로 이동합니다.");
                System.out.println();
            }
            else
            {
                System.out.println("현재 저장된 설정은 다음과 같습니다.");
                printSavedConditionSearch();
                printSavedConditionSort();
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printSavedConditionSearch()
    {
        try {
            fis = new FileInputStream(path);
            prop.load(fis);
            fis.close();
            System.out.print("검색 기준: ");
            search = Search.values()[Integer.parseInt(prop.getProperty("search"))];
            System.out.println(search.getChoice());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printSavedConditionSort()
    {
        try {
            fis = new FileInputStream(path);
            prop.load(fis);
            fis.close();
            System.out.print("정렬 기준: ");
            sort = Sort.values()[Integer.parseInt(prop.getProperty("sort"))];
            System.out.println(sort.getChoice());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveSearchCondition(int num)
    {
        try {
            fis = new FileInputStream(path);
            prop.load(fis);
            fis.close();
            fos = new FileOutputStream(path);
            prop.setProperty("search", String.valueOf(num));
            prop.store(fos,"Last changes: search");
            fos.close();
            if(num == 0)
            {
                System.out.println();
                System.out.println("검색 기준에 대한 설정을 삭제하였습니다.");
                System.out.println();
            }
            else
            {
                System.out.println();
                search = Search.values()[num];
                System.out.println("검색 기준에 대한 설정을 변경하였습니다.");
                System.out.println("현재 검색 기준: " + search.getChoice());
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveSortCondition(int num)
    {
        try {
            fis = new FileInputStream(path);
            prop.load(fis);
            fis.close();
            fos = new FileOutputStream(path);
            prop.setProperty("sort", String.valueOf(num));
            prop.store(fos, "Last changes: sort");
            fos.close();
            if(num == 0)
            {
                System.out.println();
                System.out.println("정렬 기준에 대한 설정을 삭제하였습니다.");
                System.out.println();
            }
            else
            {
                System.out.println();
                sort = Sort.values()[num];
                System.out.println("정렬 기준에 대한 설정을 변경하였습니다.");
                System.out.println("현재 정렬 기준: " + sort.getChoice());
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int loadSearchCondition()
    {
        try {
            fis = new FileInputStream(path);
            prop.load(fis);
            String value = prop.getProperty("search");
            condition = Integer.parseInt(value);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return condition;
    }

    public int loadSortCondition()
    {
        try {
            fis = new FileInputStream(path);
            prop.load(fis);
            String value = prop.getProperty("sort");
            condition = Integer.parseInt(value);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return condition;
    }

    public void autoSaveOnOff()
    {
        try {
            fis = new FileInputStream(path);
            prop.load(fis);
            fos = new FileOutputStream(path);
            if(prop.getProperty("autoSave").equals("false"))
            {
                prop.setProperty("autoSave", "true");
                prop.store(fos, "Last change: autoSave = true");
                fos.close();
                System.out.println();
                System.out.println("자동 저장 기능을 켭니다.");
                System.out.println();
            }
            else
            {
                prop.setProperty("autoSave", "false");
                prop.store(fos, "Last change: autoSave = false");
                fos.close();
                System.out.println();
                System.out.println("자동 저장 기능을 끕니다.");
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void lockUnlock()
    {
        Scanner sc = new Scanner(System.in);
        try {
            fis = new FileInputStream(path);
            prop.load(fis);
            fis.close();
            System.out.println();
            if(prop.getProperty("locked").equals("true"))
            {
                System.out.println("현재 잠금이 설정되어 있습니다.");
                System.out.println("잠금을 해제하기 위해서는 비밀번호가 필요합니다.");
                System.out.print("비밀번호 입력: ");
                String password = sc.nextLine();
                if(password.equals(prop.getProperty("password")))
                {
                    System.out.println("잠금이 해제되었습니다.");
                    fos = new FileOutputStream(path);
                    prop.setProperty("locked", "false");
                    prop.store(fos, "Last Changes: locked = false");
                    fos.close();
                }
                else
                {
                    System.out.println("비밀번호가 맞지 않습니다.");
                    System.out.println("이전으로 돌아갑니다.");
                }
                System.out.println();
            }
            else
                if(!prop.getProperty("password").isEmpty())
                {
                    System.out.println("현재 잠금이 설정되어 있지 않습니다.");
                    System.out.println("잠금을 설정하시겠습니까?");
                    System.out.print("답변 입력 ('Y', 'y', '네', '예' 이외의 대답은 모두 '아니오'로 처리합니다.): ");
                    String YN = sc.nextLine();
                    if (Objects.equals(YN, "Y") || Objects.equals(YN, "y") || Objects.equals(YN, "네") || Objects.equals(YN, "예"))
                    {
                        System.out.println("잠금이 설정되었습니다.");
                        fos = new FileOutputStream(path);
                        prop.setProperty("locked", "true");
                        prop.store(fos, "Last Changes: locked = true");
                        fos.close();
                    }
                    else
                        System.out.println("이전으로 돌아갑니다.");
                }
                else
                    System.out.println("비밀번호가 존재하지 않으면 잠금을 설정할 수 없습니다.");
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changePassword()
    {
        Scanner sc = new Scanner(System.in);
        try {
            fis = new FileInputStream(path);
            prop.load(fis);
            fis.close();
            System.out.println();
            if(!prop.getProperty("password").isEmpty())
            {
                System.out.println("비밀번호를 입력해주세요.");
                System.out.print("비밀번호: ");
                String password = sc.nextLine();
                if(password.equals(prop.getProperty("password")))
                {
                    System.out.println("1. 비밀번호를 변경합니다.");
                    System.out.println("2. 비밀번호를 삭제합니다.");
                    System.out.println("9. 뒤로가기");
                    System.out.print("번호 입력: ");
                    String choice = sc.nextLine();
                    sc.nextLine();
                    if(Objects.equals(choice, "1"))
                    {
                        System.out.println("새로운 비밀번호를 입력해주세요.");
                        System.out.print("비밀번호: ");
                        String newPassword = sc.nextLine();
                        System.out.println("다시 한 번 입력해주세요.");
                        System.out.print("비밀번호 확인: ");
                        String newPassword2 = sc.nextLine();
                        if(newPassword.equals(prop.getProperty("password")))
                        {
                            System.out.println("기존과 같은 비밀번호로는 변경할 수 없습니다.");
                        }
                        else if(newPassword.equals(newPassword2))
                        {
                            System.out.println("비밀번호가 변경되었습니다.");
                            fos = new FileOutputStream(path);
                            prop.setProperty("password", newPassword);
                            prop.store(fos, "Last Changes: new password");
                            fos.close();
                        }
                        else
                        {
                            System.out.println("두 비밀번호가 일치하지 않아 변경에 실패하였습니다.");
                        }
                        System.out.println();
                    }
                    else if(Objects.equals(choice, "2"))
                    {
                        System.out.println("정말 비밀번호를 삭제하시겠습니까?");
                        System.out.print("답변 입력 ('Y', 'y', '네', '예' 이외의 대답은 모두 '아니오'로 처리합니다.): ");
                        String YN = sc.nextLine();
                        if (Objects.equals(YN, "Y") || Objects.equals(YN, "y") || Objects.equals(YN, "네") || Objects.equals(YN, "예"))
                        {
                            System.out.println("비밀번호가 삭제되었습니다.");
                            fos = new FileOutputStream(path);
                            prop.setProperty("password", "");
                            prop.setProperty("locked", "false");
                            prop.store(fos, "Last Changes: password = null");
                            fos.close();
                        }
                        else
                            System.out.println("이전으로 돌아갑니다.");
                    }
                    else if(Objects.equals(choice, "9"))
                    {
                        System.out.println("이전으로 돌아갑니다.");
                    }
                    else
                    {
                        m.defaultMessage();
                    }
                }
                else
                {
                    System.out.println("비밀번호가 맞지 않습니다.");
                }
                System.out.println();
            }
            else
            {
                System.out.println("비밀번호를 설정합니다.");
                System.out.println("새로운 비밀번호를 입력해주세요.");
                System.out.print("비밀번호: ");
                String newPassword = sc.nextLine();
                System.out.println("다시 한 번 입력해주세요.");
                System.out.print("비밀번호 확인: ");
                String newPassword2 = sc.nextLine();
                if(newPassword.equals(newPassword2))
                {
                    System.out.println("비밀번호가 생성되었습니다.");
                    fos = new FileOutputStream(path);
                    prop.setProperty("password", newPassword);
                    prop.store(fos, "Last Changes: new password");
                    fos.close();
                }
                else
                {
                    System.out.println("두 비밀번호가 일치하지 않아 설정에 실패하였습니다.");
                }
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reset()
    {
        Scanner sc = new Scanner(System.in);
        try {
            fis = new FileInputStream(path);
            prop.load(fis);
            fis.close();
            System.out.println();
            System.out.println("비밀번호를 입력해주세요.");
            System.out.print("비밀번호: ");
            String password = sc.nextLine();
            if(password.equals(prop.getProperty("password")))
            {
                s.settingsScriptReset();
                String YN = sc.nextLine();
                if (Objects.equals(YN, "Y") || Objects.equals(YN, "y") || Objects.equals(YN, "네") || Objects.equals(YN, "예"))
                {
                    System.out.print(ScriptColors.YELLOW_BOLD_BRIGHT);
                    System.out.println("아래의 문구를 똑같이 입력하시면 초기화 및 복구 불가에 동의하신 것으로 간주하고 초기화를 진행합니다.");
                    System.out.println(ScriptColors.RED_BOLD_BRIGHT);
                    System.out.println("유의사항을 확인했습니다.");
                    System.out.println(ScriptColors.RESET);
                    String Confirm = sc.nextLine();

                    if(Objects.equals(Confirm, "유의사항을 확인했습니다."))
                    {
                        try {
                            resetDB(getConnection());
                            resetSetting();
                            s.settingsScriptResetConfirm();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.exit(0);
                    }
                }
                else
                {
                    System.out.println("이전으로 돌아갑니다.");
                }
            }
            else
            {
                m.defaultMessage();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetDB(Connection con)
    {
        try {
            PreparedStatement ps1 = con.prepareStatement("DELETE FROM CONTRACT");
            PreparedStatement ps2 = con.prepareStatement("DELETE FROM CONTRACT_GROUP");
            PreparedStatement ps3 = con.prepareStatement("SET foreign_key_checks = 0");
            PreparedStatement ps4 = con.prepareStatement("SET foreign_key_checks = 1");

            ps3.executeUpdate();
            ps1.executeUpdate();
            ps2.executeUpdate();
            ps4.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void resetSetting()
    {
        try {
            fis = new FileInputStream(path);
            prop.load(fis);
            fis.close();
            fos = new FileOutputStream(path);
            prop.setProperty("search", String.valueOf(0));
            prop.setProperty("sort", String.valueOf(0));
            prop.setProperty("autoSave", "false");
            prop.setProperty("locked", "false");
            prop.setProperty("password", "");
            prop.store(fos, "Last Changes: RESET");
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
