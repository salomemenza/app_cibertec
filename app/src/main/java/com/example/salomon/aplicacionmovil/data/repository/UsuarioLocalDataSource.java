package com.example.salomon.aplicacionmovil.data.repository;

import android.support.annotation.NonNull;

import com.example.salomon.aplicacionmovil.data.model.RecordarEntidad;
import com.example.salomon.aplicacionmovil.data.model.UsuarioR;
import com.example.salomon.aplicacionmovil.data.room.Recordar;
import com.example.salomon.aplicacionmovil.data.room.UsuarioDaoR;
import com.example.salomon.aplicacionmovil.util.AppExecutors;

import java.util.List;

public class UsuarioLocalDataSource implements UsuarioDataSource {
    private static volatile UsuarioLocalDataSource INSTANCE;
    private AppExecutors mAppExecutors;
    private UsuarioDaoR mUsuarioDao;
    private Recordar mRecordarDao;

    // Prevent direct instantiation.
    private UsuarioLocalDataSource(@NonNull AppExecutors appExecutors, @NonNull UsuarioDaoR usuarioDao) {
        mAppExecutors = appExecutors;
        mUsuarioDao = usuarioDao;
    }

    private UsuarioLocalDataSource(@NonNull AppExecutors appExecutors, @NonNull UsuarioDaoR usuarioDao, @NonNull Recordar recordarDao) {
        mAppExecutors = appExecutors;
        mUsuarioDao = usuarioDao;
        mRecordarDao = recordarDao;
    }

    public static UsuarioLocalDataSource getInstance(@NonNull AppExecutors appExecutors, @NonNull UsuarioDaoR usuarioDao) {
        if (INSTANCE == null) {
            synchronized (UsuarioLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UsuarioLocalDataSource(appExecutors, usuarioDao);
                }
            }
        }
        return INSTANCE;
    }

    public static UsuarioLocalDataSource getInstance(@NonNull AppExecutors appExecutors, @NonNull UsuarioDaoR usuarioDao, @NonNull Recordar recordarDao) {
        if (INSTANCE == null) {
            synchronized (UsuarioLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UsuarioLocalDataSource(appExecutors, usuarioDao, recordarDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getUsuarios(@NonNull final LoadUsuariosCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<UsuarioR> usuarios = mUsuarioDao.fetchAllData();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (usuarios.isEmpty()) {
                            // This will be called if the table is new or just empty.
                            callback.onDataNotAvailable();
                        } else {
                            callback.onUsuariosLoaded(usuarios);
                        }
                    }
                });
            }
        };

        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getUsuario(@NonNull final String username, @NonNull final GetUsuarioCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final UsuarioR usuario = mUsuarioDao.getRecordByUser(username);

                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (usuario != null) {
                            callback.onUsuariosLoaded(usuario);
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }
                });
            }
        };

        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getUserRemember(@NonNull final GetRecordarCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final RecordarEntidad recordar = mRecordarDao.getUserRemember();

                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (recordar != null) {
                            callback.onRecordarLoaded(recordar);
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getUsuarioRecordarByName(@NonNull final String username, @NonNull final GetRecordarCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final RecordarEntidad recordar = mRecordarDao.getRecordByUser(username);

                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (recordar != null) {
                            callback.onRecordarLoaded(recordar);
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void insertUserForRemember(@NonNull final RecordarEntidad recordar, @NonNull final GetRecordarCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final Long lngRecordar = mRecordarDao.insertOnlySingleRecord(recordar);

                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (lngRecordar != null) {
                            callback.onRecordarLoaded(recordar);
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void updateUserForRemember(@NonNull final Boolean valor, @NonNull final String username, @NonNull final GetRecordarCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mRecordarDao.updateRecord(username, valor);

                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccessProccess();
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void resetRemember(@NonNull final GetRecordarCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mRecordarDao.resetRecordar();

                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccessProccess();
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

}
