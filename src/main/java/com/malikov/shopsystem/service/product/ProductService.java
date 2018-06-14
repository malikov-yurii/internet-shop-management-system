package com.malikov.shopsystem.service.product;

import com.malikov.shopsystem.domain.Product;
import com.malikov.shopsystem.domain.ProductVariation;
import com.malikov.shopsystem.dto.ProductDto;
import com.malikov.shopsystem.dto.ProductPage;
import com.malikov.shopsystem.mapper.ProductMapper;
import com.malikov.shopsystem.mapper.ProductUpdateByNotNullFieldsMapper;
import com.malikov.shopsystem.repository.ProductRepository;
import com.malikov.shopsystem.repository.ProductVariationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.nonNull;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductVariationRepository productVariationRepository;
    private final ProductUpdateByNotNullFieldsMapper productUpdateByNotNullFieldsMapper;
    private final ProductMapper mapper;
    private final ProductAggregatorService productAggregatorService;

    public ProductService(ProductRepository productRepository, ProductVariationRepository productVariationRepository,
                          ProductUpdateByNotNullFieldsMapper productUpdateByNotNullFieldsMapper, ProductMapper mapper,
                          ProductAggregatorService productAggregatorService) {
        this.productRepository = productRepository;
        this.productVariationRepository = productVariationRepository;
        this.productUpdateByNotNullFieldsMapper = productUpdateByNotNullFieldsMapper;
        this.mapper = mapper;
        this.productAggregatorService = productAggregatorService;
    }

    public Product get(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public ProductPage getPage(int pageNumber, int pageCapacity) {
        ProductPage productPage = mapper.toPage(productRepository.findAll(PageRequest.of(pageNumber, pageCapacity)));
        productPage.setProductAggregators(productAggregatorService.findAll());
        return productPage;
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void update(ProductDto dto) {
        if (requiredDataToUpdateProductIsPresent(dto)) {
            updateProductOrProductVariation(dto);
        }
    }

    private void updateProductOrProductVariation(ProductDto dto) {
        if (nonNull(dto.getProductVariationId())) {
            updateProductVariation(dto);
        } else {
            updateProduct(dto);
        }
    }

    private void updateProduct(ProductDto dto) {

        Product product = get(dto.getProductId());
        productUpdateByNotNullFieldsMapper.update(dto, product);
    }

    private void updateProductVariation(ProductDto dto) {
        Long productVariationId = dto.getProductVariationId();
        ProductVariation productVariation = productVariationRepository.findById(productVariationId).orElse(null);
        productUpdateByNotNullFieldsMapper.update(dto, productVariation);
    }

    private boolean requiredDataToUpdateProductIsPresent(ProductDto dto) {
        return (nonNull(dto.getProductPrice()) || nonNull(dto.getProductQuantity()))
                && (nonNull(dto.getProductId()) || nonNull(dto.getProductVariationId()));
    }

}