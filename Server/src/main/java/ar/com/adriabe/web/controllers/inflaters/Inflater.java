package ar.com.adriabe.web.controllers.inflaters;


import ar.com.adriabe.services.ServiceException;

public interface Inflater<T> {
    public T inflate(T target) throws ServiceException;
}
