package com.malikov.shopsystem.repository;

public abstract class ProductRepositoryTest extends AbstractRepositoryTest {
/*
    @Autowired
    protected ProductService service;

//   // TODO: 3/14/2017 add suppliers dto tests

    @Test
    public void testSave() throws Exception {
        Product newProduct = new Product("newProductName", 100, false, 111, false, Collections.singleton(CATEGORY_KLEI), null);
        Product created = service.create(newProduct);
        newProduct.setCustomerId(created.getCustomerId());
        ProductTestData.MATCHER.assertCollectionEquals(
                Arrays.asList(POTAL_KITAJ, POTAL_NAZIONALE, SHELLAC_MANETTI, FERRARIO_ROZOVYJ, newProduct),
                service.getPage());
    }

    @Test
    public void testUpdate() throws Exception {
        Product updated = new Product(POTAL_KITAJ);
        updated.setName("Kitaj_upd");
        service.update(updated);
        ProductTestData.MATCHER.assertEquals(updated, service.get(POTAL_KITAJ.getCustomerId()));
    }

    @Test
    public void testGet() throws Exception {
        Product product = service.get(POTAL_NAZIONALE.getCustomerId());
        ProductTestData.MATCHER.assertEquals(POTAL_NAZIONALE, product);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Product> all = service.getPage();
        ProductTestData.MATCHER.assertCollectionEquals(Arrays.asList(POTAL_KITAJ, POTAL_NAZIONALE,
                SHELLAC_MANETTI, FERRARIO_ROZOVYJ), all);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(POTAL_NAZIONALE.getCustomerId());
        ProductTestData.MATCHER.assertCollectionEquals(Arrays.asList(
                POTAL_KITAJ, SHELLAC_MANETTI, FERRARIO_ROZOVYJ), service.getPage());
    }

    @Test
    public void testGetByCategoryId() throws Exception {
        Collection<Product> productsByCategoryId = service.getByCategoryId(CATEGORY_POTAL_I_ZOLOTO.getCustomerId());
        ProductTestData.MATCHER.assertCollectionEquals(Arrays.asList(POTAL_NAZIONALE, POTAL_KITAJ), productsByCategoryId);
    }

    @Test
    public void testGetAllQuantityLessThan() throws Exception {
        Collection<Product> productsWhereQuantityLessThan = service.getAllQuantityLessThan(30);
        ProductTestData.MATCHER.assertCollectionEquals(
                Arrays.asList(SHELLAC_MANETTI, POTAL_KITAJ), productsWhereQuantityLessThan);
    }*/
}
