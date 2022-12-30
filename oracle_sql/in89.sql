

SELECT
    mem_id,    mem_pass,    mem_name,
    mem_regno1,    mem_regno2,    to_char(mem_bir, 'YYYY-MM-DD')  mem_bir,
    mem_zip,    mem_add1,    mem_add2,
    mem_hometel,    mem_comtel,    mem_hp,
    mem_mail,   mem_job,    mem_like,
    mem_memorial,    to_char(mem_memorialday, 'YYYY-MM-DD') mem_memorialday,    mem_mileage,
    mem_delete
FROM    member
where mem_id = 'a001'
;

--comment on column member.mem_id is '회원아이디';
comment on column MEMBER.MEM_ID is '회원아이디';
comment on column MEMBER.MEM_PASS is '비밀번호';
comment on column MEMBER.MEM_NAME is '회원명';
comment on column MEMBER.MEM_REGNO1 is '주민번호1';
comment on column MEMBER.MEM_REGNO2 is '주민번호2';
comment on column MEMBER.MEM_BIR is '생일';
comment on column MEMBER.MEM_ZIP is '우편번호';
comment on column MEMBER.MEM_ADD1 is '주소1';
comment on column MEMBER.MEM_ADD2 is '주소2';
comment on column MEMBER.MEM_HOMETEL is '집전번';
comment on column MEMBER.MEM_COMTEL is '회사전번';
comment on column MEMBER.MEM_HP is '휴대폰';
comment on column MEMBER.MEM_MAIL is '이메일';
comment on column MEMBER.MEM_JOB is '직업';
comment on column MEMBER.MEM_LIKE is '취미';
comment on column MEMBER.MEM_MEMORIAL is '기념일';
comment on column MEMBER.MEM_MEMORIALDAY is '기념일자';
comment on column MEMBER.MEM_MILEAGE is '마일리지';
comment on column MEMBER.MEM_DELETE is '탈퇴여부';


select SUBSTR(LOWER(COLUMN_NAME),1,1) || SUBSTR(REPLACE(INITCAP(COLUMN_NAME),'_',''), 2) from USER_COL_COMMENTS
where TABLE_NAME = 'MEMBER';


CREATE OR REPLACE FUNCTION SNAKETOCAMEL(
    snake varchar2
) 
return varchar2
is
v_tmp varchar2(50);
begin
    v_tmp := SUBSTR(LOWER(snake),1,1) || SUBSTR(REPLACE(INITCAP(snake),'_',''), 2);
    return v_tmp;
end;

select '<tr><th>' || COMMENTS || '</th>' || '<td>${' || LOWER(TABLE_NAME) || '.' || SNAKETOCAMEL(COLUMN_NAME) || '}</td></tr>'
from USER_COL_COMMENTS
where TABLE_NAME = 'MEMBER';