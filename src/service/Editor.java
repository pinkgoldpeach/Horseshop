package service;

import domain.Data;

/**
 * Created by Rebeka on 2015.03.16..
 * Editor zur Erzeugung neue Pferd/Reiter-Objekte
 */
public interface Editor {

    public Data create() throws Exception;

    public Data create(String name) throws ServiceException;
}
