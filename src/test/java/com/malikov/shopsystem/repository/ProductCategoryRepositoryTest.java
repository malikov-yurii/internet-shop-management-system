package com.malikov.shopsystem.repository;

public abstract class ProductCategoryRepositoryTest extends AbstractRepositoryTest {
/*
    @Autowired
    protected ProductCategoryService service;

    @Test
    public void testSave() throws Exception {
        ProductCategory newProductCategory = new ProductCategory("newCategoryName");
        ProductCategory created = service.create(newProductCategory);
        newProductCategory.setCustomerId(created.getCustomerId());
        MATCHER.assertCollectionEquals(
                Arrays.asList(CATEGORY_KLEI, CATEGORY_LAKI, newProductCategory, CATEGORY_POTAL_I_ZOLOTO),
                service.getAll());
    }

    @Test
    public void testUpdate() throws Exception {
        ProductCategory updated = new ProductCategory(CATEGORY_KLEI);
        updated.setName("Klei_upd");
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(CATEGORY_KLEI.getCustomerId()));
    }

    @Test
    public void testGet() throws Exception {
        ProductCategory productCategory = service.get(CATEGORY_LAKI.getCustomerId());
        MATCHER.assertEquals(CATEGORY_LAKI, productCategory);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<ProductCategory> all = service.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(CATEGORY_KLEI,
                CATEGORY_LAKI, CATEGORY_POTAL_I_ZOLOTO), all);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(CATEGORY_POTAL_I_ZOLOTO.getCustomerId());
        MATCHER.assertCollectionEquals(Arrays.asList(
                CATEGORY_KLEI, CATEGORY_LAKI), service.getAll());
    }*/
}
