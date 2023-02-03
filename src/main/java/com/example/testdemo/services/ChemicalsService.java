package com.example.testdemo.services;

import com.example.testdemo.Repositories.CartRepository;
import com.example.testdemo.Repositories.ChemicalsRepository;
import com.example.testdemo.model.CartItem;
import com.example.testdemo.model.Chemicals;
import org.aspectj.weaver.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ChemicalsService {

    @Autowired
    ChemicalsRepository chemicalsRepository;

    @Autowired
    CartRepository cartRepository;

    public List<Chemicals> GetAllChemicals(){
        return chemicalsRepository.findAll();
    }

    public Optional<Chemicals> FindChemicalById(Long id){
        return chemicalsRepository.findById(id);
    }

    public void Decrement(Optional<Chemicals> chemi){
        Optional<Chemicals> chem = chemicalsRepository.findById(chemi.get().getId());
        if(chem.get().getNumberOfElements() > 0) {
            chem.get().setNumberOfElements(chem.get().getNumberOfElements() - 1);
            chemicalsRepository.save(chem.get());
        }
        else
            System.out.println("braki w magazynie");
    }

    public void Increment2(Long id){
        Optional<Chemicals> MedicineFound = chemicalsRepository.findById(id);
        if(MedicineFound.isPresent()){
            MedicineFound.get().setNumberOfElements(MedicineFound.get().getNumberOfElements() + 1);
            chemicalsRepository.save(MedicineFound.get());
        }else{
            System.out.println("Medicine Error!");
        }
    }

    public long amountToLong(int value){
        long temp = Long.valueOf(value);
        temp++;
        return temp;
    }


    public void Increment(Long id){
        List<Chemicals> ListAllMedicines = chemicalsRepository.findAll();
        Optional<CartItem> chem = cartRepository.findById(id);
        String name = chem.get().getChemicals().getName();
        Chemicals chemi = ListAllMedicines.stream().filter(lek -> lek.getName().equals(name)).findAny().orElseThrow();
        chemi.setNumberOfElements(chemi.getNumberOfElements() + 1);
        chemicalsRepository.save(chemi);
    }

    public void AddNewChemical(Chemicals chemicals){
        chemicalsRepository.save(chemicals);
    }

    public void ChangeChemicalData(Long id, String namex, Long numberOfElementsx, Double pricex){
        Optional <Chemicals> chemicalFound = chemicalsRepository.findById(id);
        if(chemicalFound.isPresent()){
            chemicalFound.get().setName(namex);
            chemicalFound.get().setNumberOfElements(numberOfElementsx);
            chemicalFound.get().setPrice(pricex);
            chemicalsRepository.save(chemicalFound.get());
        }else{
            System.out.println("Medicine Error!");
        }
    }

}
