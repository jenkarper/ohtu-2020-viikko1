determinantti <- function() {
  B <- matrix(rep(0, 100), nrow=10, ncol=10)
  for (row in 1:10) {
    for (col in 1:10) {
      if ((row+col)%%2==0) {
        B[row,col] <- 1
      } else {
        B[row,col] <- -1
      }
    }
  }
  return(det(B))
}

determinantti()

A <- diag(4)

for (row in 1:4) {
  for (col in 1:4) {
    A[row,col] <- A[row,col]+1
  }
}
A
eigen(A)



