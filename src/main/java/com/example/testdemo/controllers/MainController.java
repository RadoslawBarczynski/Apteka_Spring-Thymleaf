package com.example.testdemo.controllers;

import com.example.testdemo.Repositories.ChemicalsRepository;
import com.example.testdemo.Repositories.UsersRepository;
import com.example.testdemo.model.CartItem;
import com.example.testdemo.model.Chemicals;
import com.example.testdemo.model.NewMedicine;
import com.example.testdemo.services.CartService;
import com.example.testdemo.services.ChemicalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;


@Controller
public class MainController {

    @Autowired
    ChemicalsService chemicalsService;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    CartService cartService;

    @Autowired
    ChemicalsRepository chemicalsRepository;

    @GetMapping("/")
    public String DefPage(Model model){
        return "redirect:/login";
    }



    @GetMapping("/home")
    //@RequestMapping(value="/login", method = RequestMethod.POST)
    public String HomePage(Model model){
        List<Chemicals> findAll = chemicalsService.GetAllChemicals();
        model.addAttribute("ListMedicines", findAll);
        return "home";
    }

    @GetMapping("/contact")
    public String ContactUsPage(){
        return "contactPage";
    }

    @RequestMapping(value="searchMedicine", method = RequestMethod.POST)
    public String SearchOne(@ModelAttribute Chemicals chemicals,Model model, String name){
        List<Chemicals> ListAllMedicines = chemicalsService.GetAllChemicals();
        List<Chemicals> find = ListAllMedicines.stream().filter(lek -> lek.getName().equals(name)).toList();
        model.addAttribute("medicine", find);
        return "SearchFile";
    }

    @RequestMapping(value="showAddPage", method = RequestMethod.GET)
    public String ViewMediPage(){
        return "addPage";
    }

    @RequestMapping(value="/addMedicine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String AddMedicine(NewMedicine medicine){
        List<Chemicals> ListAllMedicines = chemicalsService.GetAllChemicals();
        List<Chemicals> find = ListAllMedicines.stream().filter(lek -> lek.getName().toLowerCase().equals(medicine.getName().toLowerCase())).toList();
        if(!find.isEmpty()) {
            Chemicals tempChem = find.get(0);
            chemicalsService.Increment2(tempChem.getId());
        }else{
            System.out.println("New Chemical");
            long temp = chemicalsService.amountToLong(ListAllMedicines.size());
            Chemicals chemi = new Chemicals(temp, medicine.getName(),medicine.getAmount(), medicine.getPrice());
            chemicalsService.AddNewChemical(chemi);
        }
        return "redirect:/home";
    }

    @RequestMapping(value="BackToHomePage", method = RequestMethod.GET)
    public String BackToHomePage(){
        return "redirect:/home";
    }

    @GetMapping("/home/{id}")
    public String AddToCart(@PathVariable Long id){
        Optional<Chemicals> newChemInCart = chemicalsService.FindChemicalById(id);
        CartItem newCartItem = new CartItem(newChemInCart.get());
        if(newCartItem.getChemicals().getNumberOfElements() > 0) {
            chemicalsService.Decrement(newChemInCart);
            cartService.AddToCartItem(newCartItem);
        }
        else {
            return "redirect:/home";
        }
        return "redirect:/home";
    }

    @RequestMapping(value="showCart", method = RequestMethod.GET)
    public String GetCartPage(Model model){
        List<CartItem> findAll = cartService.GetCartItems();
        model.addAttribute("medicine", findAll);
        return "CartPage";
    }

    @GetMapping("/showCart/{id}")
    public String DeleteFromCart(@PathVariable Long id, Model model){
        chemicalsService.Increment(id);
        cartService.DeleteCartItem(id);
        List<CartItem> findAll = cartService.GetCartItems();
        model.addAttribute("medicine", findAll);
        return "redirect:/showCart?";
    }



    @GetMapping("/update/{id}")
    public String ChangeOneMedicine(@PathVariable Long id, Model model){
        Optional <Chemicals> thischemi = chemicalsService.FindChemicalById(id);
        model.addAttribute("chemical", thischemi.get());
        return "UpdatePage";
    }

    @RequestMapping(value="/updateMedicine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String UpdateMedicine(@ModelAttribute("chemical") Chemicals chemicals, Model model){
        Optional <Chemicals> thischemix = chemicalsService.FindChemicalById(chemicals.getId());
        if(thischemix != null){
            thischemix.get().setId(chemicals.getId());
            thischemix.get().setName(chemicals.getName());
            thischemix.get().setPrice(chemicals.getPrice());
            thischemix.get().setNumberOfElements(chemicals.getNumberOfElements());
        }
        chemicalsRepository.save(thischemix.get());
        return "redirect:/home";
    }

}