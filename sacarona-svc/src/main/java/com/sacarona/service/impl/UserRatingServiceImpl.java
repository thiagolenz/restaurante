package com.sacarona.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sacarona.dao.FeedbackAverageDAO;
import com.sacarona.dao.ReceiptDAO;
import com.sacarona.dao.UserDAO;
import com.sacarona.dao.UserProfileDAO;
import com.sacarona.model.dealing.Receipt;
import com.sacarona.model.feedback.FeedbackAverage;
import com.sacarona.model.user.User;
import com.sacarona.model.user.UserProfile;
import com.sacarona.service.UserRatingService;

@Service
public class UserRatingServiceImpl implements UserRatingService {
	@Autowired private UserDAO userDAO;
	@Autowired private UserProfileDAO profileDAO;
	@Autowired private ReceiptDAO receiptDAO;
	@Autowired private FeedbackAverageDAO averageDAO;

	@Transactional
	public void recalculate(Long userId) {
		User user = userDAO.findById(User.class, userId);
		BigDecimal rate = BigDecimal.ZERO;
		if (user != null) {
			UserProfile profile = profileDAO.findByUserId(userId, user.getLang());
			if (profile != null) {
				rate = calculateUserAndProfile(user, profile, rate);
				rate = calculateCreditCart (user, profile, rate);
				rate = calculateFeedback (user, rate);
				profile.setSacaronaRating(rate);
				profileDAO.update(profile, profile.getId());
			}
		}
	}

	private BigDecimal calculateUserAndProfile (User user, UserProfile profile, BigDecimal rate) {
		rate = sumIfNotNull (rate, 1, user);
		rate = sumIfNotNull(rate, 0.5, profile.getTwitterUrl());
		rate = sumIfNotNull(rate, 0.5, profile.getLinkedinUrl());
		rate = sumIfNotNull(rate, 0.5, profile.getSecondaryEmail());
		return rate;
	}

	private BigDecimal calculateCreditCart (User user, UserProfile profile, BigDecimal rate) { 
		List<Receipt> result = receiptDAO.findAllByUser(user.getId());
		String secondaryName = profile.getSecondaryName();
		boolean hasOneValidName = false;
		boolean hasTwoValidName = false;
		boolean hasPayment = false;
		for (Receipt receipt : result) {
			hasPayment = true;
			String name = receipt.getCredicardName() ;
			if (name != null && name.trim().length() > 0) {
				if (name.equals(user.getName()))
					hasOneValidName = true;
				if (name.equals(secondaryName))
					hasTwoValidName = true;
			}
		}
		rate = sumIf(rate, 0.5, hasPayment);
		rate = sumIf(rate, 0.5, hasOneValidName);
		rate = sumIf(rate, 0.5, hasTwoValidName);
		return rate;
	}

	private BigDecimal calculateFeedback (User user, BigDecimal rate) {
		FeedbackAverage feedbackAverage = averageDAO.findByUser(user.getId());
		if (feedbackAverage != null) {
			double value = feedbackAverage.getAverageValue().doubleValue();
			value = value / 5;
			rate = sum(rate, value);
		}
		return rate;
	}

	private BigDecimal sumIfNotNull (BigDecimal rate, double value, Object object) {
		if (object != null) {
			if (object instanceof String) {
				if (object.toString().trim().length() > 0)
					return rate.add(new BigDecimal(value));
			} else {
				return rate.add(new BigDecimal(value));
			}		
		}
		return rate;
	}

	private BigDecimal sumIf (BigDecimal rate, double value, boolean flag) {
		if (flag)
			return rate.add(new BigDecimal(value));
		return rate;
	}

	private BigDecimal sum (BigDecimal rate, double value) {
		return rate.add(new BigDecimal(value));
	}
}
/**

0.5 estrelas - email           (automatico)
0.5 estrelas - facebook     (automatico)
0.5 estrelas - google+       (automatico)
0.5 estrelas - twitter          (automatico)
0.5 estrelas - linkedIn       (automatico)
0.5 estrelas - cartao validado. (pode ser cartao de otra pessoa)      (automatico?) (pagou está validado, logo automático)
----- seguridad minima Sacarona. 3.5 estrelas
0.5 estrelas - cartao de credito validado = nome facebook ou google+  (manual)
0.5 estrelas - cartao de credito validado = nome facebook e google+    (manual)  

(total até aqui 4 estrelas) 

Nota Média de feedbacks:
0 = 0 estrelas
1 = 0,2 estrelas
2 = 0,4 estrelas
3 = 0,6 estrelas
4 = 0,8 estrelas
5 = 1,0 estrelas


 */
