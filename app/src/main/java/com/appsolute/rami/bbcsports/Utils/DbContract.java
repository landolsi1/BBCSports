package com.appsolute.rami.bbcsports.Utils;

import android.provider.BaseColumns;

/**
 * Created by Rami on 1/5/2018.
 */

public final class DbContract {
    public DbContract() {
    }

    public static class SportEntries implements BaseColumns {
        public static final String TABLE_NAME = "bbc";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_IMG = "img";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_SOURCE = "Source";
    }
}
