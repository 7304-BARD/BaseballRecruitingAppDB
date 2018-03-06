package com.org.bard.RecruitingAppDB.utilities;

public class IDCounter {
    private static long account_count = 0;

    public static long getNewUid() {
        long oldCount = account_count;
        account_count++;
        return oldCount;
    }
}
