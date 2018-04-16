select t1.cusip from
(select distinct p.cusip  from prd.fhlmc_loan_sqoop f 
left outer join prd.pool_sqoop p on (f.product_id = p.product_id)
where  f.prod_type_ind = 'A' and f.current_upb>0 
) t1 
left outer join(
select distinct cusip from prd.fhlmonly 
where year=2018 and month=3 and amortization_type='ARM'
) t2
on(t1.cusip=t2.cusip)
where t2.cusip is null
)

#We can see only 4 of 32 is updated before monthly effective date 3/6/2018

select distinct(p.cusip), from_unixtime(f.last_chg_date div 1000) from prd.fhlmc_loan_sqoop f
left outer join prd.pool_sqoop p on (f.product_id = p.product_id)
where p.cusip in(
select t1.cusip from
(select distinct p.cusip  from prd.fhlmc_loan_sqoop f 
left outer join prd.pool_sqoop p on (f.product_id = p.product_id)
where  f.prod_type_ind = 'A' and f.current_upb>0 
) t1 
left outer join(
select distinct cusip from prd.fhlmonly 
where year=2018 and month=3 and amortization_type='ARM'
) t2
on(t1.cusip=t2.cusip)
where t2.cusip is null
)

order by from_unixtime(f.last_chg_date div 1000) desc
