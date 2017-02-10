package ar.com.adriabe.repositories;

import ar.com.adriabe.model.ProductFamily;
import ar.com.adriabe.model.constant.COLOR_TYPE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Mildo on 6/18/14.
 */
public interface ProductFamilyRepository extends JpaRepository<ProductFamily, Long> {

    public List<ProductFamily> findByDeleted(boolean b);

    @Query(value = "select pf from ProductFamily pf join pf.fabric fab where fab.longname like :name")
    public List<ProductFamily> findLikeName(@Param("name") String name);

    @Query(value = "select pf from ProductFamily pf join pf.fabric fab left join pf.stripe stripe where fab.id =:fabricId and stripe.id is null")
    public List<ProductFamily> findByFabric(@Param("fabricId") Long fabricId);

    @Query(value = "select pf from ProductFamily pf join pf.fabric fab left join pf.stripe stripe where fab.id =:fabricId and stripe.id =:stripeId")
    public List<ProductFamily> findByFabricAndStripe(@Param("fabricId") Long fabricId, @Param("stripeId") Long stripeId);

    @Query(value = "select pf from ProductFamily pf join pf.fabric fab left join pf.stripe stripe where fab.id =:fabricId and stripe.id is not null")
    public List<ProductFamily> findStripeFamilyByFabric(@Param("fabricId") Long fabricId);

    @Query(value = "select pf from ProductFamily pf join pf.fabric fab left join pf.stripe stripe where fab.id =:fabricId and stripe.id =:stripeId and pf.colorType=:colorType")
    ProductFamily findByFabricAndStripeAndColorType(@Param("fabricId") Long fabricId, @Param("stripeId") Long stripeId, @Param("colorType") COLOR_TYPE colorType);

    @Query(value = "select pf from ProductFamily pf join pf.fabric fab left join pf.stripe stripe where fab.id =:fabricId and stripe is null and pf.colorType=:colorType")
    ProductFamily findByFabricAndColorType(@Param("fabricId") Long fabricId, @Param("colorType") COLOR_TYPE colorType);
}
