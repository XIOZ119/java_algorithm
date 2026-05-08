-- 코드를 입력하세요
SELECT i.INGREDIENT_TYPE, SUM(f.total_order) AS 'TOTAL_ORDER'
FROM icecream_info i, first_half f
WHERE f.flavor = i.flavor
GROUP BY i.ingredient_type
ORDER BY TOTAL_ORDER ASC