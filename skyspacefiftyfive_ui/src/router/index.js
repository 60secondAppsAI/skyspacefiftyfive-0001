import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Airlines from  '@/pages/Airlines.vue';
import AirlineDetail from  '@/pages/AirlineDetail.vue';
import Flights from  '@/pages/Flights.vue';
import FlightDetail from  '@/pages/FlightDetail.vue';
import Passengers from  '@/pages/Passengers.vue';
import PassengerDetail from  '@/pages/PassengerDetail.vue';
import Bookings from  '@/pages/Bookings.vue';
import BookingDetail from  '@/pages/BookingDetail.vue';
import Tickets from  '@/pages/Tickets.vue';
import TicketDetail from  '@/pages/TicketDetail.vue';
import Aircrafts from  '@/pages/Aircrafts.vue';
import AircraftDetail from  '@/pages/AircraftDetail.vue';
import CrewMembers from  '@/pages/CrewMembers.vue';
import CrewMemberDetail from  '@/pages/CrewMemberDetail.vue';
import Airports from  '@/pages/Airports.vue';
import AirportDetail from  '@/pages/AirportDetail.vue';
import Luggages from  '@/pages/Luggages.vue';
import LuggageDetail from  '@/pages/LuggageDetail.vue';
import PaymentDetails from  '@/pages/PaymentDetails.vue';
import PaymentDetailDetail from  '@/pages/PaymentDetailDetail.vue';
import LoyaltyPrograms from  '@/pages/LoyaltyPrograms.vue';
import LoyaltyProgramDetail from  '@/pages/LoyaltyProgramDetail.vue';
import Promotions from  '@/pages/Promotions.vue';
import PromotionDetail from  '@/pages/PromotionDetail.vue';
import SeatMaps from  '@/pages/SeatMaps.vue';
import SeatMapDetail from  '@/pages/SeatMapDetail.vue';
import MealOptions from  '@/pages/MealOptions.vue';
import MealOptionDetail from  '@/pages/MealOptionDetail.vue';
import TravelClasss from  '@/pages/TravelClasss.vue';
import TravelClassDetail from  '@/pages/TravelClassDetail.vue';
import CheckinDetails from  '@/pages/CheckinDetails.vue';
import CheckinDetailDetail from  '@/pages/CheckinDetailDetail.vue';
import BoardingPasss from  '@/pages/BoardingPasss.vue';
import BoardingPassDetail from  '@/pages/BoardingPassDetail.vue';
import LoungeAccesss from  '@/pages/LoungeAccesss.vue';
import LoungeAccessDetail from  '@/pages/LoungeAccessDetail.vue';
import WeatherAlerts from  '@/pages/WeatherAlerts.vue';
import WeatherAlertDetail from  '@/pages/WeatherAlertDetail.vue';
import CustomerSupports from  '@/pages/CustomerSupports.vue';
import CustomerSupportDetail from  '@/pages/CustomerSupportDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/airlines',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/airlines',
		name: 'Airlines',
		layout: DefaultLayout,
		component: Airlines,
	},
	{
	    path: '/airline/:airlineId', 
	    name: 'AirlineDetail',
		layout: DefaultLayout,
	    component: AirlineDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/flights',
		name: 'Flights',
		layout: DefaultLayout,
		component: Flights,
	},
	{
	    path: '/flight/:flightId', 
	    name: 'FlightDetail',
		layout: DefaultLayout,
	    component: FlightDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/passengers',
		name: 'Passengers',
		layout: DefaultLayout,
		component: Passengers,
	},
	{
	    path: '/passenger/:passengerId', 
	    name: 'PassengerDetail',
		layout: DefaultLayout,
	    component: PassengerDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/bookings',
		name: 'Bookings',
		layout: DefaultLayout,
		component: Bookings,
	},
	{
	    path: '/booking/:bookingId', 
	    name: 'BookingDetail',
		layout: DefaultLayout,
	    component: BookingDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/tickets',
		name: 'Tickets',
		layout: DefaultLayout,
		component: Tickets,
	},
	{
	    path: '/ticket/:ticketId', 
	    name: 'TicketDetail',
		layout: DefaultLayout,
	    component: TicketDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/aircrafts',
		name: 'Aircrafts',
		layout: DefaultLayout,
		component: Aircrafts,
	},
	{
	    path: '/aircraft/:aircraftId', 
	    name: 'AircraftDetail',
		layout: DefaultLayout,
	    component: AircraftDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/crewMembers',
		name: 'CrewMembers',
		layout: DefaultLayout,
		component: CrewMembers,
	},
	{
	    path: '/crewMember/:crewMemberId', 
	    name: 'CrewMemberDetail',
		layout: DefaultLayout,
	    component: CrewMemberDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airports',
		name: 'Airports',
		layout: DefaultLayout,
		component: Airports,
	},
	{
	    path: '/airport/:airportId', 
	    name: 'AirportDetail',
		layout: DefaultLayout,
	    component: AirportDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/luggages',
		name: 'Luggages',
		layout: DefaultLayout,
		component: Luggages,
	},
	{
	    path: '/luggage/:luggageId', 
	    name: 'LuggageDetail',
		layout: DefaultLayout,
	    component: LuggageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/paymentDetails',
		name: 'PaymentDetails',
		layout: DefaultLayout,
		component: PaymentDetails,
	},
	{
	    path: '/paymentDetail/:paymentDetailId', 
	    name: 'PaymentDetailDetail',
		layout: DefaultLayout,
	    component: PaymentDetailDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/loyaltyPrograms',
		name: 'LoyaltyPrograms',
		layout: DefaultLayout,
		component: LoyaltyPrograms,
	},
	{
	    path: '/loyaltyProgram/:loyaltyProgramId', 
	    name: 'LoyaltyProgramDetail',
		layout: DefaultLayout,
	    component: LoyaltyProgramDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/promotions',
		name: 'Promotions',
		layout: DefaultLayout,
		component: Promotions,
	},
	{
	    path: '/promotion/:promotionId', 
	    name: 'PromotionDetail',
		layout: DefaultLayout,
	    component: PromotionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/seatMaps',
		name: 'SeatMaps',
		layout: DefaultLayout,
		component: SeatMaps,
	},
	{
	    path: '/seatMap/:seatMapId', 
	    name: 'SeatMapDetail',
		layout: DefaultLayout,
	    component: SeatMapDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/mealOptions',
		name: 'MealOptions',
		layout: DefaultLayout,
		component: MealOptions,
	},
	{
	    path: '/mealOption/:mealOptionId', 
	    name: 'MealOptionDetail',
		layout: DefaultLayout,
	    component: MealOptionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/travelClasss',
		name: 'TravelClasss',
		layout: DefaultLayout,
		component: TravelClasss,
	},
	{
	    path: '/travelClass/:travelClassId', 
	    name: 'TravelClassDetail',
		layout: DefaultLayout,
	    component: TravelClassDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/checkinDetails',
		name: 'CheckinDetails',
		layout: DefaultLayout,
		component: CheckinDetails,
	},
	{
	    path: '/checkinDetail/:checkinDetailId', 
	    name: 'CheckinDetailDetail',
		layout: DefaultLayout,
	    component: CheckinDetailDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/boardingPasss',
		name: 'BoardingPasss',
		layout: DefaultLayout,
		component: BoardingPasss,
	},
	{
	    path: '/boardingPass/:boardingPassId', 
	    name: 'BoardingPassDetail',
		layout: DefaultLayout,
	    component: BoardingPassDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/loungeAccesss',
		name: 'LoungeAccesss',
		layout: DefaultLayout,
		component: LoungeAccesss,
	},
	{
	    path: '/loungeAccess/:loungeAccessId', 
	    name: 'LoungeAccessDetail',
		layout: DefaultLayout,
	    component: LoungeAccessDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/weatherAlerts',
		name: 'WeatherAlerts',
		layout: DefaultLayout,
		component: WeatherAlerts,
	},
	{
	    path: '/weatherAlert/:weatherAlertId', 
	    name: 'WeatherAlertDetail',
		layout: DefaultLayout,
	    component: WeatherAlertDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/customerSupports',
		name: 'CustomerSupports',
		layout: DefaultLayout,
		component: CustomerSupports,
	},
	{
	    path: '/customerSupport/:customerSupportId', 
	    name: 'CustomerSupportDetail',
		layout: DefaultLayout,
	    component: CustomerSupportDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
