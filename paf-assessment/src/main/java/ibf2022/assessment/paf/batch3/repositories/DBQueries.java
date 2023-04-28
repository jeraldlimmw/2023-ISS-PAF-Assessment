package ibf2022.assessment.paf.batch3.repositories;

public class DBQueries {
    public static final String SELECT_BEER_STYLES = """
            select s.id, s.style_name, count(b.name) as count
            from styles as s
            inner join beers as b
            on s.id = b.style_id
            group by s.id
            order by count desc, s.style_name
            """;

    public static final String SELECT_BEERS_BY_STYLE = """
            select b.name as beerName, b.id as beerId, b.descript as description, 
            br.id as breweryId, br.name as breweryName
            from beers as b 
            inner join breweries as br on b.brewery_id = br.id
            where b.style_id like ?
            order by beerName
            """;

    public static final String SELECT_BEERS_BY_BREWERY = """
            select br.id, br.name, br.address1, br.address2, br.city, br.descript, br.phone, br.website, b.id as beerId, b.name as beerName, b.descript as beerDescript
            from breweries as br
            inner join beers as b on br.id = b.brewery_id
            where br.id like ?
            order by beerName
            """;

}
