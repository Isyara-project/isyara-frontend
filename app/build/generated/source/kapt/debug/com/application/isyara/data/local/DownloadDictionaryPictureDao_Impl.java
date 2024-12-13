package com.application.isyara.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class DownloadDictionaryPictureDao_Impl implements DownloadDictionaryPictureDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DownloadedDictionaryPicture> __insertionAdapterOfDownloadedDictionaryPicture;

  private final SharedSQLiteStatement __preparedStmtOfDeleteDownloadedPictureByUrl;

  public DownloadDictionaryPictureDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDownloadedDictionaryPicture = new EntityInsertionAdapter<DownloadedDictionaryPicture>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `downloaded_dictionary_pictures` (`id`,`word`,`imageUrl`,`definition`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DownloadedDictionaryPicture entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getWord() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getWord());
        }
        if (entity.getImageUrl() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getImageUrl());
        }
        if (entity.getDefinition() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDefinition());
        }
      }
    };
    this.__preparedStmtOfDeleteDownloadedPictureByUrl = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM downloaded_dictionary_pictures WHERE imageUrl = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertDownloadedPicture(final DownloadedDictionaryPicture picture,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDownloadedDictionaryPicture.insert(picture);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertPictures(final List<DownloadedDictionaryPicture> pictures,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDownloadedDictionaryPicture.insert(pictures);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteDownloadedPictureByUrl(final String url,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteDownloadedPictureByUrl.acquire();
        int _argIndex = 1;
        if (url == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, url);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteDownloadedPictureByUrl.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllDownloadedPictures(
      final Continuation<? super List<DownloadedDictionaryPicture>> $completion) {
    final String _sql = "SELECT * FROM downloaded_dictionary_pictures";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DownloadedDictionaryPicture>>() {
      @Override
      @NonNull
      public List<DownloadedDictionaryPicture> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfDefinition = CursorUtil.getColumnIndexOrThrow(_cursor, "definition");
          final List<DownloadedDictionaryPicture> _result = new ArrayList<DownloadedDictionaryPicture>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DownloadedDictionaryPicture _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpDefinition;
            if (_cursor.isNull(_cursorIndexOfDefinition)) {
              _tmpDefinition = null;
            } else {
              _tmpDefinition = _cursor.getString(_cursorIndexOfDefinition);
            }
            _item = new DownloadedDictionaryPicture(_tmpId,_tmpWord,_tmpImageUrl,_tmpDefinition);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getDownloadedPictureByUrl(final String url,
      final Continuation<? super DownloadedDictionaryPicture> $completion) {
    final String _sql = "SELECT * FROM downloaded_dictionary_pictures WHERE imageUrl = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (url == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, url);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DownloadedDictionaryPicture>() {
      @Override
      @Nullable
      public DownloadedDictionaryPicture call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWord = CursorUtil.getColumnIndexOrThrow(_cursor, "word");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfDefinition = CursorUtil.getColumnIndexOrThrow(_cursor, "definition");
          final DownloadedDictionaryPicture _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpWord;
            if (_cursor.isNull(_cursorIndexOfWord)) {
              _tmpWord = null;
            } else {
              _tmpWord = _cursor.getString(_cursorIndexOfWord);
            }
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpDefinition;
            if (_cursor.isNull(_cursorIndexOfDefinition)) {
              _tmpDefinition = null;
            } else {
              _tmpDefinition = _cursor.getString(_cursorIndexOfDefinition);
            }
            _result = new DownloadedDictionaryPicture(_tmpId,_tmpWord,_tmpImageUrl,_tmpDefinition);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
