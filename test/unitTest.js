var assert = require('assert');

var utils = require("../api/controllers/utils");
var hotelController = require("../api/controllers/hotels.controllers.js");
var reviewController = require("../api/controllers/reviews.controllers.js")
console.log(reviewController);


describe('Utils', function() {
  describe('_splitArray', function() {
    it('should split the string on semicolon', function() {
      assert.equal(utils._splitArray("hello;world")[0], 'hello');
      assert.equal(utils._splitArray("hello;world")[1], 'world');
      assert.equal(utils._splitArray("this;is;an;input")[3], 'input');
    });
  });

  describe('_splitArray', function() {
    it('returns empty string when empty string is input', function() {
      assert.equal(utils._splitArray(""), "");      
    });
  });

  describe('check_offset_and_count', function() {
    it('check if it returns true or false', function() {
      assert.equal(utils.check_offset_and_count(NaN, NaN), true);
      assert.equal(utils.check_offset_and_count(10, NaN), true);
      assert.equal(utils.check_offset_and_count(10, 10), false);
    });
  });
});


describe("HotelController", function() {
  describe("hotelsGetOne", function() {
    it("should get one hotel", function(done) {
      hotelController.hotelsGetOne({params: {hotelId:"590167adc59c38b534ee3744"}})
      done()
    })
  }) 

  describe("hotelsGetAll", function() {
    it("should get All hotels", function(done) {
      hotelController.hotelsGetAll({user:{}})
      done()
    })
  }) 

  describe("hotelsAddOne", function() {
    it("should add one hotel", function(done) {
      hotelController.hotelsAddOne({body:{name:"Taj"}})
      done()
    })
  }) 

  describe("hotelsUpdateOne", function() {
    it("should update one hotel", function(done) {
      hotelController.hotelsAddOne({params:{hotelId:"590167adc59c38b534ee3744"},body:{name:"Taj"}})
      done()
    })
  }) 
})



describe("ReviewController", function() {
  describe("reviewsGetAll", function() {
    it("should get one hotel", function(done) {
      reviewController.reviewsGetAll({params: {hotelId:"590167adc59c38b534ee3744"}})
      done()
    })
  }) 

  describe("reviewsGetOne", function() {
    it("should get All hotels", function(done) {
      reviewController.reviewsGetOne({params: {hotelId:"590167adc59c38b534ee3744", "reviewId": "slvnslbnsb190"}})
      done()
    })
  }) 
  
})
