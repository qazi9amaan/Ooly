package com.oolysolutions.oolys.Retrofit;

import com.oolysolutions.oolys.Act.OrderPlaced.ComeFullOrder;
import com.oolysolutions.oolys.Frags.Booking.OrderDetails;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @POST("a.php")
    Call<DefaultResponse> placeOrder(@Body ComeFullOrder dataModal);

    @GET("getAllBookings.php")
    Call<ArrayList<OrderDetails>> getAllOrdersFor(@Query("id") String currentUserId);
}
