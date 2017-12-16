package com.example.salomon.aplicacionmovil.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.salomon.aplicacionmovil.entidad.Usuario;
import com.example.salomon.aplicacionmovil.sqlite.DAOException;
import com.example.salomon.aplicacionmovil.sqlite.DBHelper;
import com.example.salomon.aplicacionmovil.sqlite.DataSource;
import com.example.salomon.aplicacionmovil.sqlite.Entidad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salomon on 18/11/2017.
 */

public class UsuarioDAO extends DAOException {
    private DBHelper _dbHelper;
    private static final String TAG = "UsuarioDAO";

    public UsuarioDAO(Context c) {
        Log.i("UsuarioDAO", "constructor");
        _dbHelper = new DBHelper(c);
        // Gets the data repository in write mode
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
    }

    public void insertar(Usuario usuario) throws DAOException {
        Log.i("UsuarioDAO", "insert()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();

            //values.put(Entidad.Usuario.codigousuario, usuario.getCodigoUsuario());
            values.put(Entidad.Usuario.login, usuario.getLogin());
            values.put(Entidad.Usuario.password, usuario.getPassword());
            values.put(Entidad.Usuario.apellidopaterno, usuario.getApellidoPaterno());
            values.put(Entidad.Usuario.nombre, usuario.getNombre());
            values.put(Entidad.Usuario.sexo, usuario.getSexo());

            long newRowId;
            newRowId = db.insert(DataSource.TB_USUARIO,null, values);

            //String[] args = new String[]{correo, clave};
            //db.execSQL("INSERT INTO cuenta(correo, clave) VALUES(?,?)", args);
            Log.i("UsuarioDAO", "Se insertó"+newRowId);
        } catch (Exception e) {
            throw new DAOException("UsuarioDAO: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public Usuario obtener(int xcodigo_usuario) throws DAOException {
        Log.i("UsuarioDAO", "obtener()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        Usuario modelo = new Usuario();
        // nombre de columnas a obtener
        String[] projection = {
                Entidad.Usuario.codigousuario,
                Entidad.Usuario.login,
                Entidad.Usuario.password,
                Entidad.Usuario.apellidopaterno,
                Entidad.Usuario.nombre
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = "";
        //columnas del where;
        String selection = Entidad.Usuario.codigousuario+ " = ?  ";
        //valores del where;
        String[] selectionArgs ={ String.valueOf(xcodigo_usuario)};
        try {
            //Cursor c = db.rawQuery("select * from usuario where CodigoUsuario=2", null);
            Cursor c = db.query(
                    DataSource.TB_USUARIO,  // The table to query
                    projection,                               // The columns to return
                    selection,                                // The columns for the WHERE clause
                    selectionArgs,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    sortOrder                                 // The sort order
            );

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {

                    int codigoUsuario = c.getInt(c.getColumnIndexOrThrow(Entidad.Usuario.codigousuario));
                    String login= c.getString(c.getColumnIndexOrThrow(Entidad.Usuario.login));
                    String password=c.getString(c.getColumnIndexOrThrow(Entidad.Usuario.password));
                    String apellidoPaterno = c.getString(c.getColumnIndexOrThrow(Entidad.Usuario.apellidopaterno));
                    String nombre = c.getString(c.getColumnIndexOrThrow(Entidad.Usuario.nombre));

                    modelo.setCodigoUsuario(codigoUsuario);
                    modelo.setLogin(login);
                    modelo.setPassword(password);
                    modelo.setApellidoPaterno(apellidoPaterno);
                    modelo.setNombre(nombre);

                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("UsuarioDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return modelo;
    }

    public Usuario obtenerByUser(String xuser) throws DAOException {
        Log.i("UsuarioDAO", "obtener()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        Usuario modelo = new Usuario();
        // nombre de columnas a obtener
        String[] projection = {
                Entidad.Usuario.codigousuario,
                Entidad.Usuario.login,
                Entidad.Usuario.password,
                Entidad.Usuario.apellidopaterno,
                Entidad.Usuario.nombre
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = "";
        //columnas del where;
        String selection = Entidad.Usuario.login+ " = ?  ";
        //valores del where;
        String[] selectionArgs ={ String.valueOf(xuser)};
        try {
            //Cursor c = db.rawQuery("select * from usuario where CodigoUsuario=2", null);
            Cursor c = db.query(
                    DataSource.TB_USUARIO,  // The table to query
                    projection,                               // The columns to return
                    selection,                                // The columns for the WHERE clause
                    selectionArgs,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    sortOrder                                 // The sort order
            );

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {

                    int codigoUsuario = c.getInt(c.getColumnIndexOrThrow(Entidad.Usuario.codigousuario));
                    String login= c.getString(c.getColumnIndexOrThrow(Entidad.Usuario.login));
                    String password=c.getString(c.getColumnIndexOrThrow(Entidad.Usuario.password));
                    String apellidoPaterno = c.getString(c.getColumnIndexOrThrow(Entidad.Usuario.apellidopaterno));
                    String nombre = c.getString(c.getColumnIndexOrThrow(Entidad.Usuario.nombre));

                    modelo.setCodigoUsuario(codigoUsuario);
                    modelo.setLogin(login);
                    modelo.setPassword(password);
                    modelo.setApellidoPaterno(apellidoPaterno);
                    modelo.setNombre(nombre);

                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("UsuarioDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return modelo;
    }

    public ArrayList<Usuario> listarUsuarios() throws DAOException{
        Log.i("UsuarioDAO: ", "Listar Todos");
        ArrayList<Usuario> listUsuarios = new ArrayList<Usuario>();

        SQLiteDatabase db = _dbHelper.getReadableDatabase();

        // nombre de columnas a obtener
        String[] projection = {
                Entidad.Usuario.codigousuario,
                Entidad.Usuario.login,
                Entidad.Usuario.password,
                Entidad.Usuario.apellidopaterno,
                Entidad.Usuario.nombre,
                Entidad.Usuario.sexo
        };

        String sortOrder = "";
        String selection = "";
        String[] selectionArgs ={};

        try {
            //Cursor c = db.rawQuery("select * from usuario where CodigoUsuario=2", null);
            Cursor c = db.query(
                    DataSource.TB_USUARIO,  // The table to query
                    projection,                               // The columns to return
                    selection,                                // The columns for the WHERE clause
                    selectionArgs,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    sortOrder                                 // The sort order
            );

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    Usuario modelo = new Usuario();

                    int codigoUsuario = c.getInt(c.getColumnIndexOrThrow(Entidad.Usuario.codigousuario));
                    String login= c.getString(c.getColumnIndexOrThrow(Entidad.Usuario.login));
                    String password=c.getString(c.getColumnIndexOrThrow(Entidad.Usuario.password));
                    String apellidoPaterno = c.getString(c.getColumnIndexOrThrow(Entidad.Usuario.apellidopaterno));
                    String sexo = c.getString(c.getColumnIndexOrThrow(Entidad.Usuario.sexo));
                    String nombre = c.getString(c.getColumnIndexOrThrow(Entidad.Usuario.nombre));

                    modelo.setCodigoUsuario(codigoUsuario);
                    modelo.setLogin(login);
                    modelo.setPassword(password);
                    modelo.setApellidoPaterno(apellidoPaterno);
                    modelo.setNombre(nombre);
                    modelo.setSexo(Integer.parseInt(sexo));

                    listUsuarios.add(modelo);
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("UsuarioDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return listUsuarios;
    }

    public void eliminar(Integer xcodigo_usuario) throws DAOException {
        Log.i("UsuarioDAO", "eliminar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();

        try {
            String selection = Entidad.Usuario.codigousuario + " = ? ";
            String[] selectionArgs = new String[]{String.valueOf(xcodigo_usuario)};
            db.delete(DataSource.TB_USUARIO, selection, selectionArgs);
        } catch (Exception e) {
            throw new DAOException("UsuarioDAO: Error al eliminar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public int eliminarTodos() throws DAOException {
        Log.i("UsuarioDAO", "eliminarTodos()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        int sqlresultado = 0;

        try {
            String selection = null;
            String[] selectionArgs = null;
            db.delete(DataSource.TB_USUARIO, selection, selectionArgs);
            sqlresultado = 1;
        } catch (SQLiteException e) {
            sqlresultado = 0;
            throw new DAOException("UsuarioDAO: Error al eliminar todos: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return sqlresultado;
    }

    public int getCantidadRegistros() throws DAOException {
        int cantidad=0;

        Log.i("UsuarioDAO", "getCantidadRegistros()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();

        try {
            Cursor c = db.rawQuery("select count(*) from " + DataSource.TB_USUARIO, null);
            c.moveToFirst();
            cantidad=c.getInt(0);
            c.close();
        } catch (Exception e) {
            throw new DAOException("UsuarioDAO: Error al contar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }

        return cantidad;
    }
}
