SELECT m.member_id AS MEMBER_ID, m.member_name AS MEMBER_NAME, m.gender AS GENDER, m.date_of_birth AS DATE_OF_BIRTH
FROM member_profile m
# WHERE m.date_of_birth LIKE '____-03-%'
WHERE MONTH(m.date_of_birth) = 3
AND m.tlno IS NOT NULL
AND m.gender = 'W'
ORDER BY member_id ASC
;