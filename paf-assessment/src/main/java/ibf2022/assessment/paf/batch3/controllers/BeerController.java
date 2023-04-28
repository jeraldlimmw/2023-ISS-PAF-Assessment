package ibf2022.assessment.paf.batch3.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Order;
import ibf2022.assessment.paf.batch3.models.Orders;
import ibf2022.assessment.paf.batch3.models.Style;
import ibf2022.assessment.paf.batch3.repositories.BeerRepository;
import ibf2022.assessment.paf.batch3.services.BeerService;

@Controller
public class BeerController {

	@Autowired
	private BeerRepository bRepo;

	@Autowired
	private BeerService svc;

	//TODO Task 2 - view 0
	@GetMapping(path={"/", "/index.html"})
	public String showBeerStyles(Model m) {
		List<Style> s = bRepo.getStyles();
		
		m.addAttribute("styles", s);
		return "view0";
	}

	//TODO Task 3 - view 1
	@GetMapping(path="/beer/style/{id}")
	public String showBeersInStyle(Model m, @PathVariable String id, @RequestParam String styleName) {
		List<Beer> b = bRepo.getBreweriesByBeer(Integer.parseInt(id));
		
		m.addAttribute("styleName", styleName);
		m.addAttribute("beers", b);
		return "view1";
	}
	
	//TODO Task 4 - view 2
	@GetMapping(path="/beer/brewery/{id}")
	public String showBeersInBrewery(Model m, @PathVariable String id, @RequestParam String breweryName) {
		Optional<Brewery> brewery = bRepo.getBeersFromBrewery(Integer.parseInt(id));
		if (Objects.isNull(brewery.get())) {
			m.addAttribute("breweryName", null);
		} else {
			m.addAttribute("br", brewery.get());
			m.addAttribute("breweryName", breweryName);
		}
		
		return "view2";
	}

	//TODO Task 5 - view 2, place order
	@PostMapping(path="brewery/{id}/order", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE) 
	public String orderBeers(Model m, @PathVariable String id, @RequestBody MultiValueMap<String, String> beerMap) {
		Orders orders = new Orders();
		List<Order> orderList = new ArrayList<>();

		Optional<Brewery> brewery = bRepo.getBeersFromBrewery(Integer.parseInt(id));
		List<Beer> beerList = brewery.get().getBeers();

		for (int i = 0; i < beerMap.keySet().size(); i++) {
			String beerId = String.valueOf(beerList.get(i).getBeerId());
			String q = beerMap.getFirst(beerId);
			if (q.isEmpty() || Integer.parseInt(q) < 1) {
				continue;
			} else {
				Order o = new Order(beerId, Integer.parseInt(q));
				orderList.add(o);
			}
		}

		orders.setBreweryId(Integer.parseInt(id));
		orders.setOrders(orderList);
		String orderId = svc.placeOrder(orders);

		m.addAttribute("orderId", orderId);
		return "view3";
	}
}
