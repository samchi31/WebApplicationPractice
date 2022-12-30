--1. 남녀 성별 인원수 조회
SELECT 
    CASE WHEN SUBSTR(MEM_REGNO2,1,1) IN ('1','3')
        THEN '남자'
        ELSE '여자'
    END AS 구분, 
    COUNT(*) AS CNT
FROM MEMBER
GROUP BY  
    CASE WHEN SUBSTR(MEM_REGNO2,1,1) IN ('1','3')
        THEN '남자'
        ELSE '여자'
    END;
    
--2. 지금까지 한번도 상품을 구매한 적이 없는 회원의 인적사항 조회(아이디, 이름, 이메일)
SELECT  MEM_ID, MEM_NAME, MEM_MAIL
FROM    MEMBER
WHERE   MEM_ID NOT IN (SELECT DISTINCT CART_MEMBER FROM CART);

--3. 등록된 상품이 한건도 없는 상품분류 조회(상품분류코드, 분류명)
select  lprod_gu, lprod_nm
from    lprod
where   lprod_gu not in (select distinct prod_lgu from prod);

--4. 지금까지 가장 많이 팔린 상품 조회(상품코드, 상품명, 상품분류명, 거래처명, 마일리지)
with    cartview as(
    select  cart_prod, sum(cart_qty) as total
    from    cart
    group   by cart_prod
    order   by total desc
),
with    max
select  prod_id
        , (
            select  lprod_nm
            from    lprod
            where   lprod_gu = prod_lgu     )
        , (
            select  buyer_name
            from    buyer
            where   buyer_id = prod_buyer   )
from    prod

--5. 지금까지 가장 적게 팔린 상품 조회(상품코드, 상품명, 상품분류명, 거래처명, 마일리지)
--6. 거래처 중 거래 품목 수가 가장 많은 거래처 조회(거래처코드, 거래처명, 거래처분류명, 담당자명)
--7.   거래처 중 거래 품목 수가 가장 적은 거래처 조회(거래처코드, 거래처명, 거래처분류명, 담당자명)
--8. 남녀 성별 각각 구매율이 높은 상품 조회(성별, 상품코드, 상품명, 분류명)
--9. 각 회원별 구매 총액 조회(회원아이디, 회원명, 구매총액)
--10. 각 상품별 구매왕 조회(구매왕아이디, 회원명, 구매총액)
