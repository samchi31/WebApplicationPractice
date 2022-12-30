select  b.*
from    (
        select  rownum as rnum, a.*
        from    (
            select mem_id, mem_name -- 의사컬럼 : rownum
            from member
            order by rowid desc) a
) b
where   rnum >= 11 and rnum <= 20;

WITH CARTVIEW AS(
    SELECT  count(mem_id) as cnt, cart_prod
    FROM    CART INNER JOIN MEMBER ON(CART_MEMBER = MEM_ID)
    group   by cart_prod
)
select  b.*
from    (
        select  rownum as rnum
                , a.*
        from    (
                SELECT  PROD_ID, PROD_NAME, PROD_PRICE, PROD_COST
                        , BUYER_NAME
                        , LPROD_NM
                        , nvl(cnt, 0)
                FROM    PRODVIEW LEFT OUTER JOIN CARTVIEW ON (PROD_ID = CART_PROD)
                order   by prod_id) a
        ) b
where  rnum >= 11 and rnum <= 20;

WITH CARTVIEW AS(
    SELECT  count(mem_id) as cnt, cart_prod
    FROM    CART INNER JOIN MEMBER ON(CART_MEMBER = MEM_ID)
    group   by cart_prod
)
SELECT  count(*)
FROM    PRODVIEW LEFT OUTER JOIN CARTVIEW ON (PROD_ID = CART_PROD);

--<tr>
--		<th>회원아이디</th>
--		<td>${member.memId }</td>
--	</tr>

SELECT '<tr><th>' || COMMENTS ||'</th>' ||
    '<td>${' || LOWER(TABLE_NAME) || '.' ||
    SNAKETOCAMEL(COLUMN_NAME) || '}</td></tr>'
FROM USER_COL_COMMENTS
WHERE TABLE_NAME = 'PRODVIEW';
