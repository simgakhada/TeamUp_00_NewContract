package simgakhada.teamup00.contract.contractenum;

public enum ContractSortSet
{
    NONE("SORT_BASIC", 0),
    choice_ASC("sortByNameAsc", 1),
    choiceD_ESC("sortByNameDesc", 2),
    PHONE_ASC("sortByPhoneAsc", 3),
    PHONE_DESC("sortByPhoneDesc", 4),
    BIRTH_ASC("sortByBirthAsc", 5),
    BIRTH_DESC("sortByBirthDesc", 6),
    GROUP_ASC("sortByGroupAsc", 7),
    GROUP_DESC("sortByGroupDesc", 8);

    private final String choice;
    private final int value;

    ContractSortSet(String choice, int value) {
        this.choice = choice;
        this.value = value;
    }

    public String getChoice() {
        return choice;
    }

    public int getValue() {
        return value;
    }
}
