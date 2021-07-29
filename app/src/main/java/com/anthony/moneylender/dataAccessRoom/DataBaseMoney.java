package com.anthony.moneylender.dataAccessRoom;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.anthony.moneylender.dataAccessRoom.Constantes.Constantes;
import com.anthony.moneylender.dataAccessRoom.Entidades.Administrador;
import com.anthony.moneylender.dataAccessRoom.Entidades.Cliente;
import com.anthony.moneylender.dataAccessRoom.Entidades.Historial;
import com.anthony.moneylender.dataAccessRoom.Entidades.Prestamos;

@Database(entities = {Cliente.class, Administrador.class, Prestamos.class, Historial.class},
                version = 1)
public abstract class DataBaseMoney extends RoomDatabase {

    public abstract InterfaceDao interfaceDao();
    private static  volatile DataBaseMoney INSTANCE;
    public static DataBaseMoney getInstance(Context context){
        if(INSTANCE == null){
            synchronized (DataBaseMoney.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DataBaseMoney.class, Constantes.BD).build();
                }
            }
        }

        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }

}

