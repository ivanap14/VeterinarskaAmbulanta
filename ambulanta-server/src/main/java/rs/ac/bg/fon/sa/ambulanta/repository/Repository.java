/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.sa.ambulanta.repository;

import java.util.List;

/**
 *
 * @author Korisnik
 */

public interface Repository<T> {
  
    T add(T t) throws Exception;
    T edit(T t) throws Exception;
    T delete(T t) throws Exception;
    List<T> getAll(T t) throws Exception;
    List<T> getByCriteria(T t, String condition) throws Exception;
    
}
