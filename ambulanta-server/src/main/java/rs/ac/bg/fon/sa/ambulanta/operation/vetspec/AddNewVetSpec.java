/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.sa.ambulanta.operation.vetspec;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import java.util.List;
import rs.ac.bg.fon.sa.ambulanta.operation.*;

/**
 *
 * @author Korisnik
 */
public class AddNewVetSpec extends AbstractSO{

    private VetSpec vetSpec;

    @Override
    protected void preconditions(Object param) throws Exception {

        if (param == null || !(param instanceof VetSpec)) {
            throw new Exception("Objekat pogresnog tipa.");
        }

        vetSpec = (VetSpec) param;

        checkExistVetSpec(vetSpec);
    }

    @Override
    protected void executeOperation(Object param) throws Exception {

        vetSpec = (VetSpec) repository.add((VetSpec) param);
    }

    public VetSpec getVetSpec() {
        return vetSpec;
    }

    private void checkExistVetSpec(VetSpec vetSpec) throws Exception {

        List<VetSpec> list = repository.getByCriteria(new VetSpec(),
                "WHERE idVeterinarian=" + vetSpec.getVeterinarian().getId()
                + " AND idSpecialization=" + vetSpec.getSpecialization().getId());

        if (!list.isEmpty()) {
            throw new Exception("Veterinar već ima unetu ovu specijalizaciju.");
        }
    }
    
}
