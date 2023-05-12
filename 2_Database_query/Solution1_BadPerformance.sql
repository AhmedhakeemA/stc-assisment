SELECT 
    outer_result.user_id,
    u.username,
    outer_result.training_id,
    outer_result.training_date,
    outer_result.count
FROM
    (SELECT 
        user_id, training_id, training_date, count
    FROM
        (SELECT 
        td.user_id,
            td.training_id,
            td.training_date,
            COUNT(td.training_id) AS count
    FROM
        Training_details AS td
    GROUP BY td.user_id , td.training_id , td.training_date) AS inner_result
    WHERE
        inner_result.count > 1
    ORDER BY inner_result.training_date DESC) AS outer_result
        JOIN
    user AS u ON u.user_id = outer_result.user_id
ORDER BY outer_result.training_date DESC;