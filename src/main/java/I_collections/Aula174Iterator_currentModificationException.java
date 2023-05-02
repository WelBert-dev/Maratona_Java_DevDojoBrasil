package I_collections;

// Quando desejamos modificar (remover, adicionar..) elementos de uma coleção,
// e estamos em um Looping iterando sobre essa coleção, não é possível e
// bem provavelmente ira disparar uma CurrentModificationException.

// Para resolvermos este problema existem diversas formas, e uma delas é
// utilizando o Iterator, essa classe serve para Realizarmos uma verificação
// antes de tomar alguma decisão sobre uma lista.

import java.util.ArrayList;
import java.util.List;

public class Aula174Iterator_currentModificationException {
    public static void main(String[] args) {
    List<PatientModel> patientModelList = new ArrayList<>();
    patientModelList.add(new PatientModel("Irineu", 0));
    patientModelList.add(new PatientModel("Irinéia", 1));
    patientModelList.add(new PatientModel("Wellison", 3));
    patientModelList.add(new PatientModel("Bertelli", 4));
    patientModelList.add(new PatientModel("Danielle", 5));
    patientModelList.add(new PatientModel("Elizângela", 6));

    // Sem Iterator que checa antes de executar uma ação, ocorre a seguinte exception:
//    for(PatientModel patient: patientModelList){
//        if(patient.getPriority() == 0){
//            patientModelList.remove(patient); // CurrentModificationException
//        }
//    }

    // Com Iterator que checa antes de executar uma ação (SEM programação funcional):
//        Iterator<PatientModel> patientIterator = patientModelList.iterator();
//        while (patientIterator.hasNext()) {
//            PatientModel patient = patientIterator.next();
//            if(patient.getPriority() == 0){
//                patientIterator.remove();
//            }
//        }
        // System.out.println(patientModelList); // removeu percorrendo a lista sem problemas
        //


    // Removendo com programação funcional (Mais legível):
    patientModelList.removeIf(patient -> patient.getPriority() == 0);
    System.out.println(patientModelList); // remove de uma forma mais elegante.

    }
}
