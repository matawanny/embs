#1.	The New FHLMC Monthly Loan data View (from September 2017 to current and future)
create view prd.fhlmonly_view as
select loan_identifier as loan_num,cusip,mortgage_loan_amount as mort_loan_amount,
issuance_investor_loan_upb as orig_upb, current_investor_loan_upb as current_upb,
amortization_type as amort_type, original_interest_rate as orig_note_rate,
issuance_interest_rate as pc_issuance_note_rate,current_interest_rate as note_rate,
loan_term as orig_loan_term, remaining_months_to_maturity as rem_months_to_maturity,
loan_age,interest_only_loan_indicator as io_flag,
interest_only_first_principal_and_interest_payment_date as first_pi_date,
months_to_amortization as months_to_amortize,modification_program as mod_prog,
modification_type as mod_type,number_of_modifications as number_of_mods,
loan_age_as_of_modification age_asof_mod,days_delinquent as months_deling,
loan_performance_history as loan_perf_hist, year, month
from prd.fhlmonly

#2.	The Historical FHLMC arm Loan data View (before September 2017)
create view old.fhlmc_arm_loan_view as
select loan_sequence_number as loan_num, cusip_number as cusip, 
mortgage_loan_amount as mort_loan_amount, investor_upb orig_upb, current_upb,
product_type as amort_type,note_rate_as_of_pc_issuance as orig_note_rate,
note_rate_as_of_pc_issuance as pc_issuance_note_rate,
note_rate,loan_term as orig_loan_term, rmm as rem_months_to_maturity,
loan_age,initial_interest_flag as io_flag,p_and_i_payment_date as first_pi_date,
months_to_amortize,year, month
from old.fhlmc_arm_loan;

#3.	The Historical FHLMC FRM loan data view 
create view old.fhlmc_frm_loan_view as
select loan_sequence_number as loan_num, cusip_number as cusip, 
mortgage_loan_amount as mort_loan_amount, investor_upb orig_upb, current_upb,
product_type as amort_type,note_rate as orig_note_rate,
note_rate as pc_issuance_note_rate,
note_rate,loan_term as orig_loan_term, rmm as rem_months_to_maturity,
loan_age,initial_interest_flag as io_flag,p_and_i_payment_date as first_pi_date,
months_to_amortize, year, month
from old.fhlmc_frm_loan;

#4.	The Historical FHLMC FRM loan data view with modified information (This view can include the above old.fhlmc_frm_loan_view)
create view old.fhlmc_frm_modified_loan_view as 
select l.loan_sequence_number as loan_num, l.cusip_number as cusip, 
l.mortgage_loan_amount as mort_loan_amount, l.investor_upb as orig_upb, 
l.current_upb, l.product_type as amort_type, l.note_rate as orig_note_rate,
l.note_rate as pc_issuance_note_rate,
l.note_rate,l.loan_term as orig_loan_term, l.rmm as rem_months_to_maturity,
l.loan_age,l.initial_interest_flag as io_flag, l.p_and_i_payment_date as first_pi_date,
l.months_to_amortize,m.modification_program as mod_prog,
m.modification_type as mod_type, m.number_of_modifications as number_of_mod,
m.loan_age_as_of_modification_date as age_asof_mod,
l.year,l.month
from old.fhlmc_frm_loan l
left outer join old.fhlmc_frm_modified_loan m
on(l.cusip_number=m.cusip_number and l.loan_sequence_number=m.loan_sequence_number
and l.year=m.year and l.month=m.month)

____________________________________________________


create table prd.monitors(file_name string,
file_size double,
file_lines double, 
as_of_date bigint, 
year int, 
month int, 
start_time bigint, 
process_time bigint,
records double, status string);