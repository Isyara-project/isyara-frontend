package com.application.isyara.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile DownloadedDictionaryDao _downloadedDictionaryDao;

  private volatile TranslatedTextDao _translatedTextDao;

  private volatile DownloadDictionaryPictureDao _downloadDictionaryPictureDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `downloaded_dictionary` (`url` TEXT NOT NULL, `title` TEXT NOT NULL, `localPath` TEXT NOT NULL, `imageUrl` TEXT, `localImagePath` TEXT, `timestamp` INTEGER NOT NULL, PRIMARY KEY(`url`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `translated_texts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `text` TEXT NOT NULL, `timestamp` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `downloaded_dictionary_pictures` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `url` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '59d7ea91ff7bc21f7b0059f8a13763d5')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `downloaded_dictionary`");
        db.execSQL("DROP TABLE IF EXISTS `translated_texts`");
        db.execSQL("DROP TABLE IF EXISTS `downloaded_dictionary_pictures`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsDownloadedDictionary = new HashMap<String, TableInfo.Column>(6);
        _columnsDownloadedDictionary.put("url", new TableInfo.Column("url", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedDictionary.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedDictionary.put("localPath", new TableInfo.Column("localPath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedDictionary.put("imageUrl", new TableInfo.Column("imageUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedDictionary.put("localImagePath", new TableInfo.Column("localImagePath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedDictionary.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDownloadedDictionary = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDownloadedDictionary = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDownloadedDictionary = new TableInfo("downloaded_dictionary", _columnsDownloadedDictionary, _foreignKeysDownloadedDictionary, _indicesDownloadedDictionary);
        final TableInfo _existingDownloadedDictionary = TableInfo.read(db, "downloaded_dictionary");
        if (!_infoDownloadedDictionary.equals(_existingDownloadedDictionary)) {
          return new RoomOpenHelper.ValidationResult(false, "downloaded_dictionary(com.application.isyara.data.local.DownloadedDictionary).\n"
                  + " Expected:\n" + _infoDownloadedDictionary + "\n"
                  + " Found:\n" + _existingDownloadedDictionary);
        }
        final HashMap<String, TableInfo.Column> _columnsTranslatedTexts = new HashMap<String, TableInfo.Column>(3);
        _columnsTranslatedTexts.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTranslatedTexts.put("text", new TableInfo.Column("text", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTranslatedTexts.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTranslatedTexts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTranslatedTexts = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTranslatedTexts = new TableInfo("translated_texts", _columnsTranslatedTexts, _foreignKeysTranslatedTexts, _indicesTranslatedTexts);
        final TableInfo _existingTranslatedTexts = TableInfo.read(db, "translated_texts");
        if (!_infoTranslatedTexts.equals(_existingTranslatedTexts)) {
          return new RoomOpenHelper.ValidationResult(false, "translated_texts(com.application.isyara.data.local.TranslatedText).\n"
                  + " Expected:\n" + _infoTranslatedTexts + "\n"
                  + " Found:\n" + _existingTranslatedTexts);
        }
        final HashMap<String, TableInfo.Column> _columnsDownloadedDictionaryPictures = new HashMap<String, TableInfo.Column>(2);
        _columnsDownloadedDictionaryPictures.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedDictionaryPictures.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDownloadedDictionaryPictures = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDownloadedDictionaryPictures = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDownloadedDictionaryPictures = new TableInfo("downloaded_dictionary_pictures", _columnsDownloadedDictionaryPictures, _foreignKeysDownloadedDictionaryPictures, _indicesDownloadedDictionaryPictures);
        final TableInfo _existingDownloadedDictionaryPictures = TableInfo.read(db, "downloaded_dictionary_pictures");
        if (!_infoDownloadedDictionaryPictures.equals(_existingDownloadedDictionaryPictures)) {
          return new RoomOpenHelper.ValidationResult(false, "downloaded_dictionary_pictures(com.application.isyara.data.local.DownloadedDictionaryPicture).\n"
                  + " Expected:\n" + _infoDownloadedDictionaryPictures + "\n"
                  + " Found:\n" + _existingDownloadedDictionaryPictures);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "59d7ea91ff7bc21f7b0059f8a13763d5", "c540d24dcf957c52338b6cace48d6072");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "downloaded_dictionary","translated_texts","downloaded_dictionary_pictures");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `downloaded_dictionary`");
      _db.execSQL("DELETE FROM `translated_texts`");
      _db.execSQL("DELETE FROM `downloaded_dictionary_pictures`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(DownloadedDictionaryDao.class, DownloadedDictionaryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TranslatedTextDao.class, TranslatedTextDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DownloadDictionaryPictureDao.class, DownloadDictionaryPictureDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public DownloadedDictionaryDao downloadedDictionaryDao() {
    if (_downloadedDictionaryDao != null) {
      return _downloadedDictionaryDao;
    } else {
      synchronized(this) {
        if(_downloadedDictionaryDao == null) {
          _downloadedDictionaryDao = new DownloadedDictionaryDao_Impl(this);
        }
        return _downloadedDictionaryDao;
      }
    }
  }

  @Override
  public TranslatedTextDao translatedTextDao() {
    if (_translatedTextDao != null) {
      return _translatedTextDao;
    } else {
      synchronized(this) {
        if(_translatedTextDao == null) {
          _translatedTextDao = new TranslatedTextDao_Impl(this);
        }
        return _translatedTextDao;
      }
    }
  }

  @Override
  public DownloadDictionaryPictureDao downloadDictionaryPictureDao() {
    if (_downloadDictionaryPictureDao != null) {
      return _downloadDictionaryPictureDao;
    } else {
      synchronized(this) {
        if(_downloadDictionaryPictureDao == null) {
          _downloadDictionaryPictureDao = new DownloadDictionaryPictureDao_Impl(this);
        }
        return _downloadDictionaryPictureDao;
      }
    }
  }
}
