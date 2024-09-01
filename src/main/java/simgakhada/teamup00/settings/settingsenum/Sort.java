package simgakhada.teamup00.settings.settingsenum;

public enum Sort
{
    NONE("없음", 0),
    NAME_ASC("이름 (오름차순)", 1),
    NAMED_ESC("이름 (내림차순)", 2),
    PHONE_ASC("전화번호 (오름차순)", 3),
    PHONE_DESC("전화번호 (내림차순)", 4),
    BIRTH_ASC("생년월일 (오름차순)", 5),
    BIRTH_DESC("생년월일 (내림차순)", 6),
    GROUP_ASC("그룹 이름 (오름차순)", 7),
    GROUP_DESC("그룹 이름 (내림차순)", 8);

    private final String choice;
    private final int value;

    Sort(String choice, int value) {
        this.choice = choice;
        this.value = value;
    }

    public String getChoice() {
        return choice;
    }
}
