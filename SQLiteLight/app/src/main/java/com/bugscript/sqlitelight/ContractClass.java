package com.bugscript.sqlitelight;

import android.provider.BaseColumns;

/**
 * Created by syamsundark on 05/12/17.
 */

public class ContractClass {

    public static final class nameClass implements BaseColumns {
        public static final String TABLENAME="person";
        public static final String COLUMN_PERSON_NAME="personName";
        public static final String TIME_STAMP="TimeStamp";
    }

}
