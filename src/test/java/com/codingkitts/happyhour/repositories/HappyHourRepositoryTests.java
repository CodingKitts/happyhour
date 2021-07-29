package com.codingkitts.happyhour.repositories;

public class HappyHourRepositoryTests {

    /*
        The only real "Unit" Tests we can run are ones for custom @Query we write.

        However, using an H2 DB specifically for testing means we can do some "integration"
        tests for the repo. These will basically be unit tests, and we won't use the H2 DB
        for anything other than these tests too. Making the tests super local.

        That being said we will probably need to test that the H2 DB gets setup and connected
        properly. I think I may be able to do that in the Setup part of the tests.
     */
}
