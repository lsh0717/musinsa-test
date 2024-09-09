## 구현 범위
    - 8개의 상품 카테고리 데이터 준비
    1. 고객은 카테고리 별로 최저가격인 브랜드와 가격을 조회하고 총액이 얼마인지 확인할 수 있어야 합니다.
    2. 고객은 단일 브랜드로 전체 카테고리 상품을 구매할 경우 최저가격인 브랜드와 총액이 얼마인지 확인할 수 있어야 합니다.
    3. 고객은 특정 카테고리에서 최저가격 브랜드와 최고가격 브랜드를 확인하고 각 브랜드 상품의 가격을 확인할 수 있어야 합니다.
    4. 운영자는 새로운 브랜드를 등록하고, 모든 브랜드의 상품을 추가, 변경, 삭제할 수 있어야 합니다
     
## 코드 빌드, 테스트, 실행 방법
- BrandProductApplication 실행
    
### API 리스트

1. 고객은 카테고리 별로 최저가격인 브랜드와 가격을 조회하고 총액이 얼마인지 확인할 수 있어야 합니다.    
   - @GetMapping("/api/product/lowest-price-by-category")
   - `curl -X GET "http://localhost:8080/api/product/lowest-price-by-category"`

2. 고객은 단일 브랜드로 전체 카테고리 상품을 구매할 경우 최저가격인 브랜드와 총액이 얼마인지 확인할 수 있어야 합니다.        
   - @GetMapping("/api/product/lowest-price-by-brand") 
   - `curl -X GET "http://localhost:8080/api/product/lowest-price-by-brand"`

3. 고객은 특정 카테고리에서 최저가격 브랜드와 최고가격 브랜드를 확인하고 각 브랜드 상품의 가격을 확인할 수 있어야 합니다.      
   - @GetMapping("/api/product/price-by-category"), RequestParam("category")
   - (category : TOP, OUTER, PANTS, SNEAKERS, BAG, HAT, SOCKS, ACCESSORY 중 하나)
   - `curl -X GET "http://localhost:8080/api/product/price-by-category?categoryName=TOP"`
        


## 구현 방법
    - ProductDao, BrandDao에 add, find, update, delete 구현 (unit test 구현)

## Test (unit / Integration)
    - ProductDaoTest, BrandDaoTest 구현

