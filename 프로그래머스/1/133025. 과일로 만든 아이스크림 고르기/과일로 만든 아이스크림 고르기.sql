SELECT f.FLAVOR
FROM first_half f, icecream_info i
WHERE f.flavor = i.flavor
AND f.TOTAL_ORDER > 3000
AND i.INGREDIENT_TYPE = 'fruit_based'
ORDER BY f.TOTAL_ORDER DESC