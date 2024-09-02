package simgakhada.teamup00.contract.contractenum;

public enum ContractSortSet
{
    NONE("SORT_BASIC", 0),
    NAME_ASC("sortByNameAsc", 1),
    NAME_DESC("sortByNameDesc", 2),
    PHONE_ASC("sortByPhoneAsc", 3),
    PHONE_DESC("sortByPhoneDesc", 4),
    BIRTH_ASC("sortByBirthAsc", 5),
    BIRTH_DESC("sortByBirthDesc", 6),
    GROUP_ASC("sortByGroupAsc", 7),
    GROUP_DESC("sortByGroupDesc", 8);

    private final String query;
    private final int value;

    ContractSortSet(String query, int value) {
        this.query = query;
        this.value = value;
    }

    public String getQuery() {
        return query;
    }

}
