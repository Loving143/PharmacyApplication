package com.pharmacy.entity;

import java.time.LocalDateTime;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CustomerLoginHistory {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "customer_id", nullable = false)
	    private Customer customer;

	    @Column(name = "ip_address", nullable = false)
	    private String ipAddress;

	    @Column(name = "location")
	    private String location;

	    @Column(name = "device_used")
	    private String deviceUsed;

	    @Column(name = "login_time")
	    private LocalDateTime loginTime;

	    @Column(name = "logout_time")
	    private LocalDateTime logoutTime;

	    @Column(name = "failed_attempts", nullable = false)
	    private int failedAttempts = 0;

	    @Column(name = "account_locked", nullable = false)
	    private boolean accountLocked = false;

	    @Column(name = "lock_expiry_time")
	    private LocalDateTime lockExpiryTime;

	    // Constructors
	    public CustomerLoginHistory() {}

	    public CustomerLoginHistory(Customer customer, String ipAddress, String location, String deviceUsed, LocalDateTime loginTime) {
	        this.customer = customer;
	        this.ipAddress = ipAddress;
	        this.location = location;
	        this.deviceUsed = deviceUsed;
	        this.loginTime = loginTime;
	    }
	    
	    public CustomerLoginHistory(Customer customer, Map<String ,Object>map) {
	        this.customer = customer;
	        this.ipAddress =(String) map.get("ipAddress");
	        this.location =(String) map.get("location");
	        this.deviceUsed = (String) map.get("deviceUsed");
//	        this.loginTime =(LocalDateTime) map.get("loginTime");
//	        this.logoutTime =(LocalDateTime) map.get("logOutTime");
	        this.failedAttempts = 0;
	        this.accountLocked = false;
//	        this.lockExpiryTime = (LocalDateTime) map.get("lockExpiryTime");
	    }

	    // Getters and Setters
	    public Long getId() { return id; }

	    public Customer getCustomer() { return customer; }

	    public void setCustomer(Customer customer) { this.customer = customer; }

	    public String getIpAddress() { return ipAddress; }

	    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

	    public String getLocation() { return location; }

	    public void setLocation(String location) { this.location = location; }

	    public String getDeviceUsed() { return deviceUsed; }

	    public void setDeviceUsed(String deviceUsed) { this.deviceUsed = deviceUsed; }

	    public LocalDateTime getLoginTime() { return loginTime; }

	    public void setLoginTime(LocalDateTime loginTime) { this.loginTime = loginTime; }

	    public LocalDateTime getLogoutTime() { return logoutTime; }

	    public void setLogoutTime(LocalDateTime logoutTime) { this.logoutTime = logoutTime; }

	    public int getFailedAttempts() { return failedAttempts; }

	    public void setFailedAttempts(int failedAttempts) { this.failedAttempts = failedAttempts; }

	    public boolean isAccountLocked() { return accountLocked; }

	    public void setAccountLocked(boolean accountLocked) { this.accountLocked = accountLocked; }

	    public LocalDateTime getLockExpiryTime() { return lockExpiryTime; }

	    public void setLockExpiryTime(LocalDateTime lockExpiryTime) { this.lockExpiryTime = lockExpiryTime; }
	}