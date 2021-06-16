<template>
  <v-item-group>
    <v-container>
      <v-row>
        <v-col
          v-for="category in categories"
          :key="category.id"
          cols="12"
          md="4"
        >
          <v-card
          >
            <div class="d-flex flex-no-wrap justify-space-between">
              <div>
                <v-card-title
                  class="headline"
                  v-text="category.name"
                ></v-card-title>
                <v-card-actions>
                    <v-btn color="success" @click="getProducts(category.id)">
                        products
                    </v-btn>
                </v-card-actions>
              </div>
            </div>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-item-group>
</template>

<script>
export default {
    data: () => ({
        categories: []
    }),
    methods: {
        getCategories() {
            this.$http.get("api/categories").then(response => {
                this.categories = response.data;
            })
        },
        getProducts(categoryId) {
            this.$router.push({ name: 'Products', params: { categoryId: categoryId }});
        }
    },
    created() {
        this.getCategories();
    }
}
</script>