package simgakhada.teamup00.settings.settingsenum;

public enum Search
{
    SEARCH_NONE("없음", 0),
    NAME("이름", 1),
    PHONE_WHOLE("전화번호 (전체)", 2),
    PHONE_4DIGITS("전화번호 (4자리)", 3),
    EMAIL("이메일", 4),
    ADDRESS("주소", 5);

    private final String choice;
    private final int value;

    Search(String choice, int value) {
        this.choice = choice;
        this.value = value;
    }

    public String getChoice() {
        return choice;
    }
}
