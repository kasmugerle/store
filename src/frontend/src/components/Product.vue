<template>
  <v-item-group>
    <v-container>
      <h1>{{ message }}</h1>
      <v-autocomplete
        v-model="model"
        :items="items"
        :search-input.sync="search"
        cache-items
        color="white"
        hide-no-data
        hide-selected
        item-text="name"
        label="Products"
        placeholder="Search for Products"
        prepend-icon="mdi-database-search">
      </v-autocomplete>
      <v-divider></v-divider>
      <v-row>
        <v-col
          v-for="prod in prods"
          :key="prod.id"
          cols="12"
          md="2"
        >
          <v-card
          >
            <div class="d-flex flex-no-wrap justify-space-between">
              <div>
                <v-card-title
                  class="headline"
                  v-text="prod.name"
                ></v-card-title>
                
                <v-card-text>Id: {{ prod.id }}</v-card-text>
                <v-card-text>Price: {{ prod.price }}</v-card-text>

                <v-card-actions>
                  <v-text-field
                    v-model="amount"
                    label="Amount"
                  ></v-text-field>
                  <v-btn fab color="success" @click="findCart(prod.id, amount)">
                    <v-icon>
                      mdi-cart
                    </v-icon>
                  </v-btn>
                </v-card-actions>
              </div>
            </div>
          </v-card>
        </v-col>
      </v-row>
      <v-snackbar
      v-model="snackbar"
    >
      {{ message }}
      <v-btn
        color="success"
        text
        @click="snackbar = false"
      >
        Close
      </v-btn>
    </v-snackbar>
    </v-container>
  </v-item-group>
</template>

<script>
export default {
    data: () => ({
        products: [],
        carts: [],
        cartId: null,
        amount: null,
        items: [],
        prods: [],
        isLoading: false,
        model: '',
        search: '',
        message: '',
        snackbar: false
    }),
    computed: {
        categoryId() {
            return this.$route.params.categoryId
        },
        isLoggedIn: function() { return this.$store.getters.isLoggedIn }
    },
    watch: {
      search(val) { 
        this.prods = [];
        var p = this.products.find(product => product.name === val);
        this.prods.push(p)
      }
    },
    methods: {
        getProducts() {
            this.$http.get(`/api/categories/${this.categoryId}`).then(response => {
              this.products = response.data.products;
              this.prods = this.products;
              for(var i=0; i < this.products.length; i++) {
                this.items.push(this.products[i].name);
              }
            });
        },

        addCartProduct(cartId, productId, a) {
          this.$http.post(`/api/carts/${ +cartId }/products/${ +productId }`, {
              amount: +a
            }).then(response => {
              this.message = response.data.message;
            }).then(() => {
              this.$router.replace({ name: "CartProducts", params: {cartId: +cartId }, query: {q: this.message }});
            })
        },
        
        findCart(productId, a) { 
          if(!this.isLoggedIn) {
            this.message = "You must sign up to create a cart!";
            this.snackbar = true;
            this.$router.push('/login')
          }
          this.$http.get('api/carts/recent').then(response => {
            this.cartId = response.data.id;
          }).then(() => {
            this.addCartProduct(this.cartId, productId, a);
          })
        },
    },
    created() {
      this.getProducts();
    }
}
</script>