package simgakhada.teamup00.search.searchenum;

public enum SearchSet
{
    NONE("none", 0),
    NAME("searchByName", 1),
    PHONE_WHOLE("searchByPhone", 2),
    PHONE_4DIGITS("searchByPhone4Digits", 3),
    EMAIL("searchByEmail", 4),
    ADDRESS("searchByAddress", 5);

    private final String query;
    private final int value;

    SearchSet(String query, int value) {
        this.query = query;
        this.value = value;
    }

    public String getQuery() {
        return query;
    }
}
