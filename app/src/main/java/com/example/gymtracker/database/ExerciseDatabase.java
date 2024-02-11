//TODO: Delete this class later
//package com.example.gymtracker.database;
//
//import android.content.Context;
//
//import androidx.annotation.NonNull;
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//import androidx.sqlite.db.SupportSQLiteDatabase;
//
//import com.example.gymtracker.entity.Exercise;
//import com.example.gymtracker.interfaces.ExerciseDao;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//@Database(entities = {Exercise.class}, version = 1)
//public abstract class ExerciseDatabase extends RoomDatabase {
//    private static ExerciseDatabase INSTANCE;
//
//    public abstract ExerciseDao exerciseDao();
//
//    private static final int NUMBER_OF_THREADS = 4;
//    public static final ExecutorService databaseWriteExecutor =
//            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
//
//    public static synchronized ExerciseDatabase getInstance(final Context context) {
//        if (INSTANCE == null) {
//            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                    ExerciseDatabase.class, "ExerciseDatabase")
//                    .fallbackToDestructiveMigration()
//                    //.addCallback(roomDatabaseCallback)
//                    .build();
//        }
//        return INSTANCE;
//    }
//
//    //Populate Data When Database is Created
//    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//
//            // Populate initial data
//            Executors.newSingleThreadExecutor().execute(new Runnable() {
//                @Override
//                public void run() {
//                    // Empty for now - maybe add Data from API?
//
//                }
//            });
//        }
//    };
//
//}
