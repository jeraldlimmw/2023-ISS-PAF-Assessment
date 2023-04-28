package ibf2022.assessment.paf.batch3.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Style;

import static ibf2022.assessment.paf.batch3.repositories.DBQueries.*;

@Repository
public class BeerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// DO NOT CHANGE THE SIGNATURE OF THIS METHOD
	public List<Style> getStyles() {
		// TODO: Task 2
		List<Style> styles = new ArrayList<>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_BEER_STYLES);
		
		while (rs.next()) {
			Style s = new Style();
			s.setStyleId(rs.getInt("id"));
			s.setName(rs.getString("style_name"));
			s.setBeerCount(rs.getInt("count"));
			styles.add(s);
		}

		return styles;
	}
		
	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public List<Beer> getBreweriesByBeer(/* You can add any number parameters here */ Integer id) {
		// TODO: Task 3
		List<Beer> beers = new ArrayList<>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_BEERS_BY_STYLE, id);

		while(rs.next()) {
			Beer b = new Beer();
			b.setBeerId(rs.getInt("beerId"));
			b.setBeerName(rs.getString("beerName"));
			b.setBeerDescription(rs.getString("description"));
			b.setBreweryId(rs.getInt("breweryId"));
			b.setBreweryName(rs.getString("breweryName"));
			beers.add(b);
		}

		return beers;
	}

	// DO NOT CHANGE THE METHOD'S NAME OR THE RETURN TYPE OF THIS METHOD
	public Optional<Brewery> getBeersFromBrewery(Integer id) {
		// TODO: Task 4
		SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_BEERS_BY_BREWERY, id);

		if (Objects.isNull(rs.first())) {
			return null;
		}

		Brewery b = new Brewery();
		List<Beer> bList = new ArrayList<>();
		if (rs.first()){
			b.setBreweryId(rs.getInt("id"));
			b.setName(rs.getString("name"));
			b.setAddress1(rs.getString("address1"));
			b.setAddress2(rs.getString("address2"));
			b.setCity(rs.getString("city"));
			b.setPhone(rs.getString("phone"));
			b.setWebsite(rs.getString("website"));
			b.setDescription(rs.getString("descript"));
			
			Beer b1 = new Beer();
			b1.setBeerId(rs.getInt("beerId"));
			b1.setBeerName(rs.getString("beerName"));
			b1.setBeerDescription(rs.getString("beerDescript"));
			bList.add(b1);
		}

		while(rs.next()) {
			Beer beer = new Beer();
			beer.setBeerId(rs.getInt("beerId"));
			beer.setBeerName(rs.getString("beerName"));
			beer.setBeerDescription(rs.getString("beerDescript"));
			bList.add(beer);
		}
		b.setBeers(bList);
		return Optional.of(b);
	}
}
