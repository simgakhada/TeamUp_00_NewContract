package simgakhada.teamup00.settings;

import simgakhada.teamup00.settings.settingsenum.Search;
import simgakhada.teamup00.settings.settingsenum.Sort;

import java.io.*;
import java.util.Properties;

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
public class SettingsDAO
{
    File path = new File("src/main/resources/config/settings.properties");
    Properties props = new Properties();
    FileInputStream fis;
    FileOutputStream fos;
    Search search;
    Sort sort;
    int condition = 0;

    public void printSavedCondition()
    {
        try {
            fis = new FileInputStream(path);
            props.load(fis);
            fis.close();
            System.out.println();
            System.out.println("현재 저장된 검색 및 정렬 기준에 대한 설정을 확인합니다.");
            if(props.getProperty("search").equals("0") && props.getProperty("sort").equals("0"))
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
            props.load(fis);
            fis.close();
            System.out.print("검색 기준: ");
            search = Search.values()[Integer.parseInt(props.getProperty("search"))];
            System.out.println(search.getChoice());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void printSavedConditionSort()
    {
        try {
            fis = new FileInputStream(path);
            props.load(fis);
            fis.close();
            System.out.print("정렬 기준: ");
            sort = Sort.values()[Integer.parseInt(props.getProperty("sort"))];
            System.out.println(sort.getChoice());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveSearchCondition(int num)
    {
        try {
            fis = new FileInputStream(path);
            props.load(fis);
            fis.close();
            fos = new FileOutputStream(path);
            props.setProperty("search", String.valueOf(num));
            props.store(fos,"Settings about search and sort.");
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
            props.load(fis);
            fis.close();
            fos = new FileOutputStream(path);
            props.setProperty("sort", String.valueOf(num));
            props.store(fos, "Settings about search and sort.");
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
            props.load(fis);
            String value = props.getProperty("search");
            condition = Integer.parseInt(value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return condition;
    }

    public int loadSortCondition()
    {
        try {
            fis = new FileInputStream(path);
            props.load(fis);
            String value = props.getProperty("sort");
            condition = Integer.parseInt(value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return condition;
    }
}
