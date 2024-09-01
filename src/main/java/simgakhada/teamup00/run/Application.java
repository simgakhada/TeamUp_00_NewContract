package simgakhada.teamup00.run;

/**
 * "메인"
 * 프로젝트의 메인 메소드입니다.
 * 메인 메소드는 최대한 간결하게 표현하는 것을 목표로 합니다.
 * 그런 의미로 평소 메인 메소드 안에서 사용하던 while 및 switch... case도
 * 별도의 클래스(MainController)를 선언하여 해당 부분으로 이전하였습니다.
 */
public class Application
{
    public static void main(String[] args)
    {
        MainController.run();
    }
}
