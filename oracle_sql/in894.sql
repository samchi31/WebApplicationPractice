desc prod;

select mem_id from member;

select prod_id, prod_lgu from prod;

select MAX(substr(prod_id,2)) from prod where prod_lgu = 'P101';

select  'P' || nvl(to_char((MAX(substr(prod_id,2))+1)),substr('P401',2)||'000001')
from    prod
where   prod_lgu = 'P401';

select  prod_lgu || lpad(to_number(substr(max(prod_id), 5)) + 1, 6, '0')
from    prod
where   prod_lgu = 'P401'
group   by prod_lgu;

select  'P909' || 
    lpad(nvl(to_number(substr(max(prod_id), 5)), 0) + 1, 6, '0')
from    prod
where   prod_lgu = 'P909';

select  ', ' || COLUMN_NAME
from    COLS
where   TABLE_NAME = 'PROD';

-- insert 문 value 절에 select 사용 시에는 select 로 얻은 값은 cursor 이므로 values 를 빼야한다

INSERT INTO prod (
    prod_id,
    prod_name,
    prod_lgu,
    prod_buyer,
    prod_cost,
    prod_price,
    prod_sale,
    prod_outline,
    prod_detail,
    prod_img,
    prod_totalstock,
    prod_insdate,
    prod_properstock,
    prod_size,
    prod_color,
    prod_delivery,
    prod_unit,
    prod_qtyin,
    prod_qtysale,
    prod_mileage
) VALUES (
    :v0,
    :v1,
    :v2,
    :v3,
    :v4,
    :v5,
    :v6,
    :v7,
    :v8,
    :v9,
    :v10,
    :v11,
    :v12,
    :v13,
    :v14,
    :v15,
    :v16,
    :v17,
    :v18,
    :v19
);

--#{memId,jdbcType=VARCHAR}
SELECT ', #{' || SNAKETOCAMEL(COLUMN_NAME) ||',jdbcType=' ||
    CASE
        WHEN DATA_TYPE = 'VARCHAR2' THEN 'VARCHAR'
        WHEN DATA_TYPE = 'NUMBER' THEN 'NUMERIC'
        ELSE DATA_TYPE
    END || '}'
FROM COLS
WHERE TABLE_NAME = 'PROD';

--<tr>
--		<th>회원아이디</th>
--		<td>
--			<input class="form-control" type="text" required  name="memId" value="${member.memId}" />
--			<span class="text-danger">${errors.memId }</span>
--		</td>
--	</tr>
SELECT '<tr><th>' || COMMENTS || '</th><td>' ||
    '<input class="form-control" type="' ||
    CASE
    WHEN DATA_TYPE = 'DATE' OR DATA_TYPE = 'NUMBER' THEN LOWER(DATA_TYPE)
    ELSE 'text'
    END || '" ' ||
    DECODE(NULLABLE, 'N', ' required ', '') ||
    'name="' || SNAKETOCAMEL(A.COLUMN_NAME) || '" value="${' ||
    LOWER(A.TABLE_NAME) || '.' || SNAKETOCAMEL(A.COLUMN_NAME) || '}" />' ||
    '<span class="text-danger">${errors.' || SNAKETOCAMEL(A.COLUMN_NAME) ||
    '}</span></td></tr>'
FROM COLS A LEFT OUTER JOIN USER_COL_COMMENTS B
        ON (A.TABLE_NAME = B.TABLE_NAME AND A.COLUMN_NAME = B.COLUMN_NAME)
WHERE A.TABLE_NAME = 'PROD';
--	<tr>
--		<th>회원아이디</th>
--		<td>
--			<input class="form-control" type="text" name="memId" />
--		</td>
--	</tr>

--varchar2, char, date, number
SELECT '<tr><th>' || COMMENTS || '</th>' ||
 '<td><input class="form-control" type="' ||
 CASE
    WHEN DATA_TYPE = 'DATE' OR DATA_TYPE='NUMBER'
        THEN LOWER(DATA_TYPE)
    ELSE 'text' END  ||
  '" name="' || SNAKETOCAMEL(A.COLUMN_NAME)   || '" />' ||
  '</td></tr>'
FROM COLS A LEFT OUTER JOIN USER_COL_COMMENTS B 
    ON(A.TABLE_NAME = B.TABLE_NAME AND A.COLUMN_NAME = B.COLUMN_NAME)
WHERE A.TABLE_NAME = 'MEMBER';

alter table member
add(MEM_ROLE VARCHAR2(20) default 'ROLE_USER');

select mem_id, mem_role from member;

update member
set mem_role = 'ROLE_ADMIN'
where mem_id = 'z001';
commit;

desc prod;
select prod_img from prod;