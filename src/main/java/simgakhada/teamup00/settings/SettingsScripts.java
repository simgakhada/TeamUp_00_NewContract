package simgakhada.teamup00.settings;

import simgakhada.teamup00.etc.ScriptColors;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * SettingsScripts
 * SettingsController 클래스에서 사용되는 연속된 print(ln)을
 * 가시성을 위해 별도의 클래스로 분리하였습니다.
 */
public class SettingsScripts
{
    File path = new File("src/main/resources/config/settings.properties");
    Properties prop = new Properties();
    FileInputStream fis;

    public void settingsScriptController()
    {
        try {
            fis = new FileInputStream(path);
            prop.load(fis);
            fis.close();
            System.out.println("[설정] 검색 및 정렬 기준에 대한 설정을 관리합니다.");
            System.out.println("기능을 선택해주세요.");
            System.out.println("1. 현재 설정 확인");
            System.out.println("2. 검색 기준 설정 변경");
            System.out.println("3. 정렬 기준 설정 변경");
            if(prop.getProperty("autoSave").equals("false"))
            {
                System.out.println("4. 자동 저장 기능 켜기");
            }
            else
            {
                System.out.println("4. 자동 저장 기능 끄기");
            }
            if(prop.getProperty("locked").equals("false"))
            {
                System.out.println("5. 프로그램 잠금 설정");
            }
            else
            {
                System.out.println("5. 프로그램 잠금 해제");
            }
            if(prop.getProperty("password").isEmpty())
            {
                System.out.println("6. 새 비밀번호 설정");
            }
            else
            {
                System.out.println("6. 비밀번호 변경 / 삭제");
                System.out.println(ScriptColors.RED_BOLD_BRIGHT);
                System.out.println("R. 전화번호부 초기화");
                System.out.println(ScriptColors.RESET);
            }
            System.out.println("9. 설정 종료 및 메인 메뉴로 이동");
            System.out.print("번호 입력: ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void settingsScriptSearch()
    {
        System.out.println();
        System.out.println("검색 기준에 대한 설정 변경을 시도합니다.");
        System.out.println("아래의 선택지는 검색의 기준을 의미하며, 다음 검색부터 해당 기준을 우선으로 하여 검색합니다.");
        System.out.println("0번을 선택할 경우 현재 저장된 검색 기준이 초기화되며, 다음 검색 시 검색 기준을 반드시 선택해야 합니다.");
        System.out.println("0. 초기화");
        System.out.println("1. 이름");
        System.out.println("2. 전화번호 (전체)");
        System.out.println("3. 전화번호 (4자리)");
        System.out.println("4. 이메일");
        System.out.println("5. 주소");
        System.out.println("9. 뒤로가기");
        System.out.print("번호 입력: ");
    }

    public void settingsScriptSort()
    {
        System.out.println();
        System.out.println("정렬 기준에 대한 설정 변경을 시도합니다.");
        System.out.println("아래의 선택지는 정렬의 기준을 의미하며, 다음 검색부터 해당 기준을 우선으로 하여 정렬합니다.");
        System.out.println("0번을 선택할 경우 현재 저장된 정렬 기준이 초기화되며, 다음 검색 시 정렬 기준을 반드시 선택해야 합니다.");
        System.out.println("0. 초기화");
        System.out.println("1. 이름 (오름차순)    \t 2. 이름 (내림차순)");
        System.out.println("3. 전화번호 (오름차순) \t 4. 전화번호 (내림차순)");
        System.out.println("5. 생년월일 (오름차순) \t 6. 생년월일 (내림차순)");
        System.out.println("7. 그룹 이름 (오름차순) \t 8. 그룹 이름 (내림차순)");
        System.out.println("9. 뒤로가기");
        System.out.print("번호 입력: ");
    }

    public void settingsScriptReset()
    {
        System.out.println("전화번호부의 모든 데이터를 초기화합니다.");
        System.out.println("초기화 후 데이터는 복구할 수 없으므로 신중한 선택이 필요합니다.");
        System.out.println("정말 초기화하시겠습니까?");
        System.out.print("답변 입력 ('Y', 'y', '네', '예' 이외의 대답은 모두 '아니오'로 처리합니다.): ");
    }

    public void settingsScriptResetConfirm() throws InterruptedException
    {
        printWithDelays("초기화를 시작합니다.\n", TimeUnit.MILLISECONDS, 100);
        printWithDelays("그룹을 모두 삭제합니다.\n", TimeUnit.MILLISECONDS, 100);
        printWithDelays("그룹을 모두 삭제하였습니다.\n", TimeUnit.MILLISECONDS, 100);
        printWithDelays("연락처를 모두 삭제합니다.\n", TimeUnit.MILLISECONDS, 100);
        printWithDelays("연락처를 모두 삭제하였습니다.\n", TimeUnit.MILLISECONDS, 100);
        printWithDelays("저장된 설정을 모두 삭제합니다.\n", TimeUnit.MILLISECONDS, 100);
        printWithDelays("설정을 모두 삭제하였습니다.\n", TimeUnit.MILLISECONDS, 100);
        printWithDelays("비밀번호를 삭제합니다.\n", TimeUnit.MILLISECONDS, 100);
        printWithDelays("비밀번호를 삭제하였습니다.\n", TimeUnit.MILLISECONDS, 100);
        printWithDelays("초기화를 완료했습니다.\n", TimeUnit.MILLISECONDS, 100);
        System.out.println("프로그램을 종료합니다.");
    }

    public static void printWithDelays(String data, TimeUnit unit, long delay)
            throws InterruptedException
    {
        for (char ch : data.toCharArray())
        {
            System.out.print(ch);
            unit.sleep(delay);
        }
    }
}
